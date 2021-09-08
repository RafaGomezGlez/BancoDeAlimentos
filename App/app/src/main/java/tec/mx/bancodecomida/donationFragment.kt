package tec.mx.bancodecomida

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tec.mx.bancodecomida.databinding.FragmentFeedBinding
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import com.paypal.android.sdk.payments.PaymentConfirmation
import tec.mx.bancodecomida.databinding.FragmentDonationBinding
import tec.mx.bancodecomida.databinding.FragmentMilestonesBinding
import androidx.core.app.ActivityCompat.startActivityForResult

//import org.graalvm.compiler.hotspot.replacements.HotSpotReplacementsUtil.config

import android.content.Intent
import androidx.activity.result.registerForActivityResult
import java.math.BigDecimal


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


private var _binding: FragmentDonationBinding? = null
private val binding get() = _binding!!

class donationFragment : Fragment(R.layout.fragment_donation) {

    private var param1: String? = null
    private var param2: String? = null

    val clientKey = "Adxi8mN3MdFwqPYdz_qK_VW4KxKYZtR0dTxpwhyDitzWm7AJMy_sUBGCob1r_FVf9Cwsco8HRpRFFt95"
    val PAYPAL_REQUEST_CODE = 117

    val PayPalConfiguration = com.paypal.android.sdk.payments.PayPalConfiguration()
        .environment("ENVIRONMENT_SANDBOX").clientId(clientKey)


     override fun onCreateView(inflater: LayoutInflater,
                               container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {
         //Create the object binding, it's similar to R. ...., whenever you want
         //to call a View in your xml file, just use binding.View
         _binding = FragmentDonationBinding.inflate(inflater, container, false)
         val view = binding.root

         var edtAmount = binding.EdtAmount

         //Iniciar servicio Paypal



         binding.BtnPay.setOnClickListener{
             getPayment()
         }



         return view
     }

    private fun getPayment() {
        val amount: String = binding.EdtAmount.text.toString()

        // Creating a paypal payment on below line.

        // Creating a paypal payment on below line.
        val payment = PayPalPayment(
            BigDecimal(amount), "Mex", "Donation",
            PayPalPayment.PAYMENT_INTENT_SALE
        )

        // Creating Paypal Payment activity intent

        // Creating Paypal Payment activity intent
        val intent = Intent(activity, PaymentActivity::class.java)

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, "config")

        // Putting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)

        // Starting the intent activity for result
        // the request code will be used on the method onActivityResult

        // Starting the intent activity for result
        // the request code will be used on the method onActivityResult
       startActivityForResult(intent,PAYPAL_REQUEST_CODE)
    }


    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}