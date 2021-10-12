package tec.mx.bancodecomida.Credentials

//import tec.mx.bancodecomida.presentationFragmentTestDirections
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat.recreate
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import tec.mx.bancodecomida.MainActivity
import tec.mx.bancodecomida.R
import tec.mx.bancodecomida.databinding.FragmentPresentationBinding
import java.util.*

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
            val action = PresentationFragmentDirections.actionPresentationFragmentTestToSignInFragmentTest()
            findNavController().navigate(action)
        }
        //Listener for the Login button that will send you to LogInFragment
        binding.buttonLogIn.setOnClickListener{
            val action = PresentationFragmentDirections.actionPresentationFragmentTestToLoginFragmentText()
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