package tec.mx.bancodecomida.Credentials

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import tec.mx.bancodecomida.R
import tec.mx.bancodecomida.databinding.FragmentLoginBinding


//Using binding library in order to avoid using getElementById
private var _binding: FragmentLoginBinding? = null
private val binding get() = _binding!!
private lateinit var auth: FirebaseAuth


//fragment_log_in, is the id of the fragment, it's located in xml file
class LoginFragment : Fragment(R.layout.fragment_login) {

    //This is the correct syntax if we want to use binding library
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View {
        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root


        //Listener for the getStarted button, when it's pushed the data is send to
        // FeedFragment
        binding.buttonGetStarted.setOnClickListener{
            //Constant because i don't know how to do avoid the error
            login(view)
        }

        return view
    }

    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun login(view: View?){
        auth = Firebase.auth

        Firebase.auth.signInWithEmailAndPassword(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString()
        )
            .addOnCompleteListener(activity as Activity){

                if(it.isSuccessful){
                    val username = " "

                    //Create an action that will be sent into FeedFragment
                    val action = LoginFragmentDirections.actionLoginFragmentTextToMainActivity(username,binding.emailEditText.text.toString(),binding.passwordEditText.text.toString())
                    findNavController().navigate(action)
                    Log.d("FIREBASE LOGIN", "Login exitoso")
                } else {
                    Log.e("FIREBASE LOGIN", "error: ${it.exception?.message}")
                }
            }
    }

}
