package tec.mx.bancodecomida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tec.mx.bancodecomida.databinding.FragmentSignInBinding

//Using binding library in order to avoid using getElementById
private var _binding: FragmentSignInBinding? = null
private val binding get() = _binding!!

class SignInFragment : Fragment(R.layout.fragment_sign_in){
    //This is the correct syntax if we want to use binding library
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View? {
        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root

        //Listener for the Sign In button, when it's pushed the data is send to
        // FeedFragment
        binding.buttonSignIn.setOnClickListener{
            val username = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val action = SignInFragmentDirections.actionSignInFragmentToFeedFragment(username,email,password)
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