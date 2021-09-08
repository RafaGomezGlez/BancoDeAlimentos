package tec.mx.bancodecomida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tec.mx.bancodecomida.databinding.FragmentMilestonesBinding

//Using binding library in order to avoid using getElementById
private var _binding: FragmentMilestonesBinding? = null
private val binding get() = _binding!!

class milestones : Fragment(R.layout.fragment_milestones){
    //This is the correct syntax if we want to use binding library
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View? {
        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View
        _binding = FragmentMilestonesBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.donateButton.setOnClickListener{
            val action = milestonesDirections.actionMilestonesFragmentToDonationFragment()
            findNavController().navigate(action)
        }

        return view
    }
    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}