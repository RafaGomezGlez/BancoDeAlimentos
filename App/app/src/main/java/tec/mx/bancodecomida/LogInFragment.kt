package tec.mx.bancodecomida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tec.mx.bancodecomida.databinding.FragmentLogInBinding

private var _binding: FragmentLogInBinding? = null
private val binding get() = _binding!!

class LogInFragment : Fragment(R.layout.fragment_log_in) {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ) : View? {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        val view = binding.root



        binding.buttonGetStarted.setOnClickListener{
            val username = "Rafa"
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val action = LogInFragmentDirections.actionLogInFragmentToFeedFragment(email,password, username)
            findNavController().navigate(action)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}