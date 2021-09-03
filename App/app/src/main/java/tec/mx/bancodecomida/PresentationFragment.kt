package tec.mx.bancodecomida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tec.mx.bancodecomida.databinding.FragmentPresentationBinding

//Using binding library in order to avoid using getElementById
private var _binding: FragmentPresentationBinding? = null
private val binding get() = _binding!!

//fragment_log_in, is the id of the fragment, it's located in xml file
class PresentationFragment : Fragment(R.layout.fragment_presentation) {
    //This is the correct syntax if we want to use binding library
    override fun onCreateView(inflater: LayoutInflater,
                               container: ViewGroup?,
                               savedInstanceState: Bundle?
    ) : View? {
        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View
        _binding = FragmentPresentationBinding.inflate(inflater, container, false)
        val view = binding.root


        //Listener for the getStarted button that will send you to SignInFragment
       binding.buttonGetStarted.setOnClickListener{
            val action = PresentationFragmentDirections.actionPresentationFragmentToSignInFragment()
            findNavController().navigate(action)
        }
        //Listener for the Login button that will send you to LogInFragment
        binding.buttonLogIn.setOnClickListener{
            val action = PresentationFragmentDirections.actionPresentationFragmentToLogInFragment()
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