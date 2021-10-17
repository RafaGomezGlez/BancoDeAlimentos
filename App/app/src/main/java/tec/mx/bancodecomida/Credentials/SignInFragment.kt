    package tec.mx.bancodecomida.Credentials

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tec.mx.bancodecomida.Credentials.Model.User
import tec.mx.bancodecomida.R
import tec.mx.bancodecomida.databinding.FragmentSignInBinding
import java.util.regex.Pattern

//Using binding library in order to avoid using getElementById
private var _binding: FragmentSignInBinding? = null
private val binding get() = _binding!!
private lateinit var auth: FirebaseAuth

private var firstNameIsValid : Boolean = false
private var lastNameIsValid : Boolean = false
private var emailIsValid : Boolean = false
private var passwordIsValid : Boolean = false

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

        val firstName = binding.firstNameEditText
        val firstNameError = binding.firstNameErrorTextView
        firstName.afterTextChangedDelayed {
            if (firstName.text.length>1){
                firstNameIsValid=true
                firstNameError.text = ""
            }else{
                firstNameIsValid = false
                firstNameError.text = "First name must be at least 2 characters long"
            }
            manageSignUpButton()
        }

        val lastName = binding.lastNameEditText
        val lastNameError = binding.lastNameErrorTextView
        lastName.afterTextChangedDelayed {
            if (lastName.text.length>1){
                lastNameIsValid=true
                lastNameError.text = ""
            }else{
                lastNameIsValid = false
                lastNameError.text = "Last name must be at least 2 characters long"
            }
            manageSignUpButton()
        }

        val email = binding.emailEditText
        val emailError = binding.emailErrorTextView
        email.afterTextChangedDelayed {
            emailIsValid = isValidEmail(email.text.toString())
            if (!emailIsValid){
                emailError.text = "Invalid email address"
            }else{
                emailError.text = ""
            }
            manageSignUpButton()
        }

        val password = binding.passwordEditText
        val passwordError = binding.passwordErrorTextView
        password.afterTextChangedDelayed {
            passwordIsValid = isValidPassword(password.text.toString())
            if(!passwordIsValid){
                passwordError.text = "Invalid password"
            }else{
                passwordError.text = ""
            }
            manageSignUpButton()
        }

        return view
    }
    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun signIn(view: View?){
        val db = Firebase.firestore
        auth = Firebase.auth

        Firebase.auth.createUserWithEmailAndPassword(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString()
        )
            .addOnCompleteListener(activity as Activity) {
                if(it.isSuccessful){
                    val user = User(binding.firstNameEditText.text.toString() , binding.lastNameEditText.text.toString(), binding.emailEditText.text.toString())
                    db.collection("Users").document().set(user)
                    val action = SignInFragmentDirections.actionSignInToMainActivity(binding.firstNameEditText.text.toString(), binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
                    findNavController().navigate(action)
                    Log.d("FIREBASE", "Registro exitoso")
                } else {

                    Log.w("FIREBASE", "Registro fracaso ${it.exception}")
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

    private fun isValidEmail(email: String): Boolean =
        email.isNotEmpty() && Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
                    ")+"
        ).matcher(email).matches()

    private fun isValidPassword(password: String): Boolean {
        val passwordREGEX = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$");
        return passwordREGEX.matcher(password).matches()
    }

    private fun manageSignUpButton(){
        binding.buttonSignIn.isEnabled = firstNameIsValid && lastNameIsValid && emailIsValid && passwordIsValid
    }

}