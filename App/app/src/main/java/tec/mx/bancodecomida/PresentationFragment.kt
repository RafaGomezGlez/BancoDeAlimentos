package tec.mx.bancodecomida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tec.mx.bancodecomida.databinding.FragmentPresentationBinding


private var _binding: FragmentPresentationBinding? = null
private val binding get() = _binding!!


class PresentationFragment : Fragment(R.layout.fragment_presentation) {
    override fun onCreateView(inflater: LayoutInflater,
                               container: ViewGroup?,
                               savedInstanceState: Bundle?
    ) : View? {
        _binding = FragmentPresentationBinding.inflate(inflater, container, false)
        val view = binding.root


       binding.buttonGetStarted.setOnClickListener{
            val action = PresentationFragmentDirections.actionPresentationFragmentToSignInFragment()
            findNavController().navigate(action)
        }
        binding.buttonLogIn.setOnClickListener{
            val action = PresentationFragmentDirections.actionPresentationFragmentToLogInFragment()
            findNavController().navigate(action)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}