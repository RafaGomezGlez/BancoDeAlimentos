package tec.mx.bancodecomida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tec.mx.bancodecomida.databinding.FragmentSignInBinding

private var _binding: FragmentSignInBinding? = null
private val binding get() = _binding!!

class SignInFragment : Fragment(R.layout.fragment_sign_in){
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.buttonSignIn.setOnClickListener{
            val username = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val action = SignInFragmentDirections.actionSignInFragmentToFeedFragment(username,email,password)
            findNavController().navigate(action)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}