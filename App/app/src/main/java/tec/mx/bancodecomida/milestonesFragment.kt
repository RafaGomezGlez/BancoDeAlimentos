package tec.mx.bancodecomida

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import tec.mx.bancodecomida.databinding.FragmentMilestonesBinding

//Using binding library in order to avoid using getElementById
private var _binding: FragmentMilestonesBinding? = null
private val binding get() = _binding!!

class milestones : Fragment(R.layout.fragment_milestones) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMilestonesBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.donateButton.setOnClickListener{
            val action = milestonesDirections.actionMilestonesFragmentToDonationFragment()
            findNavController().navigate(action)
        }

        //Progress bar settings
        modifyProgressBars()

        // Inflate the layout for this fragment
        return view
    }

    private fun modifyProgressBars() {
        val docRef =  FirebaseFirestore.getInstance()
            .collection("bamxDonations").document("mainAccount")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("PRUEBA", "DocumentSnapshot data: ${document.data}")
                    Log.d("PRUEBA", "${document["monthGoal"]}")
                    val accountGoals = document.toObject<BamxAccountInfo>()

                    if(accountGoals != null) {
                        //Max progress bar settings
                        binding.progressBarMonthGoal.max = accountGoals.monthGoal!!
                        binding.progressBarWeekGoal.max = accountGoals.weeklyGoal!!
                        binding.progressBarRecord.max = accountGoals.record!!

                        //Current progress settings
                        ObjectAnimator.ofInt(binding.progressBarMonthGoal, "progress", accountGoals.totalMoney!!)
                            .setDuration(2000)
                            .start()
                        ObjectAnimator.ofInt(binding.progressBarWeekGoal, "progress", accountGoals.totalMoney!!)
                            .setDuration(2000)
                            .start()
                        ObjectAnimator.ofInt(binding.progressBarRecord, "progress", accountGoals.totalMoney!!)
                            .setDuration(2000)
                            .start()

                        //Setup textview of Goals
                        binding.monthGoalVal.text = "$"+accountGoals.monthGoal.toString()
                        binding.weekGoalVal.text = "$"+accountGoals.weeklyGoal.toString()
                        binding.recordGoalVal.text = "$"+accountGoals.record.toString()

                        Log.wtf("Account goal m",accountGoals.monthGoal.toString())
                        Log.wtf("Account goal w",accountGoals.weeklyGoal.toString())
                        Log.wtf("Account goal r",accountGoals.record.toString())
                        Log.wtf("money",accountGoals.totalMoney.toString())

                    }
                } else {
                    Log.d("PRUEBA", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("DEBUG", "get failed with ", exception)
            }
    }

    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    data class BamxAccountInfo(
         val monthGoal: Int? = null,
         val totalMoney: Int? = null,
         val weeklyGoal: Int? = null,
         val record: Int? = null
    )
}
