package tec.mx.bancodecomida.Settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tec.mx.bancodecomida.R
import tec.mx.bancodecomida.databinding.FragmentSignInBinding

private var _binding: FragmentSignInBinding? = null
private val binding get() = _binding!!

class settings_profile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_profile, container, false)
    }

}