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
import tec.mx.bancodecomida.databinding.FragmentSignInBinding

//Using binding library in order to avoid using getElementById
private var _binding: FragmentSignInBinding? = null
private val binding get() = _binding!!
private lateinit var auth: FirebaseAuth


class SignInFragment : Fragment(R.layout.fragment_sign_in){
    //This is the correct syntax if we want to use binding library
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View {
        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root

        //Listener for the Sign In button, when it's pushed the data is send to
        // FeedFragment
        binding.buttonSignIn.setOnClickListener{
           signIn(view)
        }

        return view
    }
    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun signIn(view: View?){
        auth = Firebase.auth

        Firebase.auth.createUserWithEmailAndPassword(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString()
        )
            .addOnCompleteListener(activity as Activity) {
                if(it.isSuccessful){
                    val action = SignInFragmentDirections.actionSignInToMainActivity(binding.nameEditText.text.toString(), binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
                    findNavController().navigate(action)
                    Log.d("FIREBASE", "Registro exitoso")
                } else {

                    Log.w("FIREBASE", "Registro fracaso ${it.exception}")
                }
            }
    }

}