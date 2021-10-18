package tec.mx.bancodecomida.Milestones

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


private var _binding: FragmentDonationBinding? = null
private val binding get() = _binding!!

class donationFragment : Fragment(R.layout.fragment_donation) {

    private val moneyAccountRef = Firebase.firestore.collection("bamxDonations").document("mainAccount")

    private var param1: String? = null
    private var param2: String? = null
    lateinit var payPalButton: PayPalButton
    lateinit var donationAmount: String

     @SuppressLint("SetTextI18n")
     override fun onCreateView(inflater: LayoutInflater,
                               container: ViewGroup?,
                               savedInstanceState: Bundle?): View {
         //Create the object binding, it's similar to R. ...., whenever you want
         //to call a View in your xml file, just use binding.View
         _binding = FragmentDonationBinding.inflate(inflater, container, false)
         val view = binding.root

         val payPalButton=binding.payPalButton
         val donationAmountText = binding.EdtAmount
         var donationAmount = 0
         //Donation buttons
         val donate50 = binding.donate50
         val donate100 = binding.donate100
         val donate200 = binding.donate200
         val donate500 = binding.donate500
         val donate1000 = binding.donate1000
         val updateBalance = binding.updateFirestore

         var donated50 = false
         var donated100 = false
         var donated200 = false
         var donated500 = false
         var donated1000 = false

         //Paypal setup
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
                                 value = donationAmount.toString()//Por el momento solo se pueden hacer donaciones de la cantidad especificada aqui.
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
         //Button config
         donate50.setOnClickListener {
             if(!donated50) {
                 donated50 = true
                 donationAmount += 50
                 donationAmountText.text = "$$donationAmount MXN"
             }else{
                 donated50 = false
                 donationAmount -= 50
                 donationAmountText.text = "$$donationAmount MXN"
             }
         }

         donate100.setOnClickListener {
             if(!donated100) {
                 donated100 = true
                 donationAmount += 100
                 donationAmountText.text = "$$donationAmount MXN"
             }else{
                 donated100 = false
                 donationAmount -= 100
                 donationAmountText.text = "$$donationAmount MXN"
             }
         }
         donate200.setOnClickListener {
             if(!donated200) {
                 donated200 = true
                 donationAmount += 200
                 donationAmountText.text = "$$donationAmount MXN"
             }else{
                 donated200 = false
                 donationAmount -= 200
                 donationAmountText.text = "$$donationAmount MXN"
             }
         }
         donate500.setOnClickListener {
             if(!donated500) {
                 donated500 = true
                 donationAmount += 500
                 donationAmountText.text = "$$donationAmount MXN"
             }else{
                 donated500 = false
                 donationAmount -= 500
                 donationAmountText.text = "$$donationAmount MXN"
             }
         }
         donate1000.setOnClickListener {
             if(!donated1000) {
                 donated1000 = true
                 donationAmount += 1000
                 donationAmountText.text = "$$donationAmount MXN"
             }else{
                 donated1000 = false
                 donationAmount -= 1000
                 donationAmountText.text = "$$donationAmount MXN"
             }
         }

        updateBalance.setOnClickListener {
            saveFirestore(donationAmount)
        }

         return view
     }



    private fun saveFirestore(donationMoney: Int){
        val totalMoneyCalc=donationMoney.toDouble()
        val docRef =  FirebaseFirestore.getInstance()
            .collection("bamxDonations")
            .document("mainAccount")

        docRef.update("totalMoney", FieldValue.increment(totalMoneyCalc))
            .addOnSuccessListener {
                Log.d("Logrado", "saveFirestore: valido ")
            }.addOnFailureListener { exception ->
                Log.d("Error", "get failed with ", exception)
            }
    }


    // Method of the binding library
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
