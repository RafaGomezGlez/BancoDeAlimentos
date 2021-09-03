package tec.mx.bancodecomida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tec.mx.bancodecomida.databinding.FragmentLogInBinding

//Using binding library in order to avoid using getElementById
private var _binding: FragmentLogInBinding? = null
private val binding get() = _binding!!

//fragment_log_in, is the id of the fragment, it's located in xml file
class LogInFragment : Fragment(R.layout.fragment_log_in) {

    //This is the correct syntax if we want to use binding library
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View? {
        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        val view = binding.root


        //Listener for the getStarted button, when it's pushed the data is send to
        // FeedFragment
        binding.buttonGetStarted.setOnClickListener{
            //Constant because i don't know how to do avoid the error
            val username = "Rafa"
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            //Create an action that will be sent into FeedFragment
            val action = LogInFragmentDirections.actionLogInFragmentToFeedFragment(email,password, username)
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