package tec.mx.bancodecomida.Milestones

//import org.graalvm.compiler.hotspot.replacements.HotSpotReplacementsUtil.config

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.OrderIntent
import com.paypal.checkout.createorder.UserAction
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.Order
import com.paypal.checkout.order.PurchaseUnit
import com.paypal.checkout.paymentbutton.PayPalButton
import tec.mx.bancodecomida.R
import tec.mx.bancodecomida.databinding.FragmentDonationBinding
import java.math.BigDecimal


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


private var _binding: FragmentDonationBinding? = null
private val binding get() = _binding!!

class donationFragment : Fragment(R.layout.fragment_donation) {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var payPalButton: PayPalButton
    lateinit var donationAmount: String

     override fun onCreateView(inflater: LayoutInflater,
                               container: ViewGroup?,
                               savedInstanceState: Bundle?): View {
         //Create the object binding, it's similar to R. ...., whenever you want
         //to call a View in your xml file, just use binding.View
         _binding = FragmentDonationBinding.inflate(inflater, container, false)
         val view = binding.root

         val payPalButton=binding.payPalButton
         val donationAmount = binding.EdtAmount
         payPalButton.setup(
             createOrder = CreateOrder { createOrderActions ->
                 val order = Order(
                     intent = OrderIntent.CAPTURE,
                     appContext = AppContext(
                         userAction = UserAction.PAY_NOW
                     ),
                     purchaseUnitList = listOf(
                         PurchaseUnit(
                             amount = Amount(
                                 currencyCode = CurrencyCode.MXN,
                                 value = "200" //Por el momento solo se pueden hacer donaciones de 200 pesos.
                             )
                         )
                     )
                 )

                 createOrderActions.create(order)
             },
             onApprove = OnApprove { approval ->
                 approval.orderActions.capture { captureOrderResult ->
                     Log.i("CaptureOrder", "CaptureOrderResult: $captureOrderResult")
                 }
             }
         )
         return view
     }




    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}