package tec.mx.bancodecomida.Settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.local.QueryEngine
import com.google.firebase.ktx.Firebase
import tec.mx.bancodecomida.R
import tec.mx.bancodecomida.databinding.FragmentSettingsProfileBinding
import tec.mx.bancodecomida.databinding.FragmentSignInBinding


private var _binding: FragmentSettingsProfileBinding? = null
private val binding get() = _binding!!
private lateinit var auth: FirebaseAuth


class settings_profile : Fragment(R.layout.fragment_settings_profile) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth

        val db = Firebase.firestore

        _binding = FragmentSettingsProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        db.collection("Users")
            .whereEqualTo("userEmail", auth.currentUser?.email.toString())
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    binding.nameText.text = document.data["usernameFirstName"].toString()
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Email", "Error getting documents: ", exception)
            }

        val query = db.collection("Users").whereEqualTo("userEmail", auth.currentUser?.email.toString()).get()
        binding.emailText.text = auth.currentUser?.email.toString()
        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}