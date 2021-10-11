package tec.mx.bancodecomida

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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
        binding.progressBarGuadalajara.max = 1000
        binding.progressBarTlaquepaque.max = 1000
        binding.progressBarTonala.max = 1000

        val currentProgressGuadalajara = 800
        val currentProgressTlaquepaque = 350
        val currentProgressTonala = 500

        ObjectAnimator.ofInt(binding.progressBarGuadalajara, "progress", currentProgressGuadalajara)
            .setDuration(2000)
            .start()

        ObjectAnimator.ofInt(binding.progressBarTlaquepaque, "progress", currentProgressTlaquepaque)
            .setDuration(2000)
            .start()

        ObjectAnimator.ofInt(binding.progressBarTonala, "progress", currentProgressTonala)
            .setDuration(2000)
            .start()

        // Inflate the layout for this fragment
        return view
    }

    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
