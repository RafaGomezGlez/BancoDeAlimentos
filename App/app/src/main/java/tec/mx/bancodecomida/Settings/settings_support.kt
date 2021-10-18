package tec.mx.bancodecomida.Settings

import android.os.Bundle
import android.os.ParcelFileDescriptor.open
import android.system.Os.open
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.findNavController
import tec.mx.bancodecomida.MainActivity
import tec.mx.bancodecomida.R
import tec.mx.bancodecomida.databinding.FragmentSettingsSupportBinding


//Using binding library in order to avoid using getElementById
private var _binding: FragmentSettingsSupportBinding? = null
private val binding get() = _binding!!

class settings_support : Fragment(R.layout.fragment_settings_support) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View
        _binding = FragmentSettingsSupportBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.useConditionsTextView.setOnClickListener{
            (activity as MainActivity?)?.loadAgreements2()
        }

        binding.dataPoliciesTextView.setOnClickListener{
            (activity as MainActivity?)?.loadAgreements1()
        }

        return view
    }

    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}