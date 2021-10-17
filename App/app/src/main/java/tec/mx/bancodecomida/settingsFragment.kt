package tec.mx.bancodecomida

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import tec.mx.bancodecomida.Credentials.CredentialsActivity
import tec.mx.bancodecomida.databinding.FragmentSettingsBinding

//Using binding library in order to avoid using getElementById
private var _binding: FragmentSettingsBinding? = null
private val binding get() = _binding!!

class settingsFragment : Fragment(R.layout.fragment_settings){
    //This is the correct syntax if we want to use binding library
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View {
        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        //Listeners for each textView in settings
        binding.profileTextView.setOnClickListener{
            val action = settingsFragmentDirections.actionSettingsFragmentToSettingsProfile()
            findNavController().navigate(action)
        }
        binding.supportTextView.setOnClickListener{
            val action = settingsFragmentDirections.actionSettingsFragmentToSettingsSupport()
            findNavController().navigate(action)
        }
        binding.logOutTextView.setOnClickListener{
            Firebase.auth.signOut()
            val activityIntent = Intent(activity as Activity, CredentialsActivity::class.java)
            startActivity(activityIntent)
        }

        return view
    }
    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}