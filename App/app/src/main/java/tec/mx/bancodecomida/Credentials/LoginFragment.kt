package tec.mx.bancodecomida.Credentials

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings.Global.getString
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.material.Text
import androidx.compose.ui.res.stringResource
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

private lateinit var email: TextView
private lateinit var password: TextView
private lateinit var errorMessage: TextView
private  lateinit var logInButton: Button

private var emailIsValid : Boolean = false
private var passwordIsValid : Boolean = false

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

        errorMessage = binding.errorMessageTextView
        logInButton = binding.buttonGetStarted

        email = binding.emailEditText
        email.afterTextChangedDelayed {
            emailIsValid = email.text.length>0
            if(!emailIsValid){
                errorMessage.setText(R.string.error_all_fields_required)
            }else if(passwordIsValid){
                errorMessage.text = ""
            }
            manageLogInButton()
        }
        password = binding.passwordEditText
        password.afterTextChangedDelayed {
            passwordIsValid = password.text.length>0
            if(!passwordIsValid){
                errorMessage.setText(R.string.error_all_fields_required)
            }else if(emailIsValid){
                errorMessage.text = ""
            }
            manageLogInButton()
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
                    errorMessage.setText(R.string.error_email_pass)
                }
            }
    }

    fun TextView.afterTextChangedDelayed(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            var timer: CountDownTimer? = null

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                timer?.cancel()
                timer = object : CountDownTimer(1000, 1500) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        afterTextChanged.invoke(editable.toString())
                    }
                }.start()
            }
        })
    }

    private fun manageLogInButton(){
        if(emailIsValid && passwordIsValid){
            logInButton.isEnabled = true
        }else{
            logInButton.isEnabled = false
        }
    }

}
