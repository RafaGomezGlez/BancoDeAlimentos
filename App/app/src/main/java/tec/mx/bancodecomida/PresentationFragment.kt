package tec.mx.bancodecomida

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_presentation.*


class PresentationFragment : Fragment(R.layout.fragment_presentation) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_GetStarted.setOnClickListener{
            val action = PresentationFragmentDirections.actionPresentationFragmentToLogInFragment()
            findNavController().navigate(action)
        }
        button_LogIn.setOnClickListener{
            val action = PresentationFragmentDirections.actionPresentationFragmentToSignInFragment()
            findNavController().navigate(action)
        }
    }
}