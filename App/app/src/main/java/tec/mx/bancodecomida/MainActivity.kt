package tec.mx.bancodecomida

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.Environment
import com.paypal.checkout.config.SettingsConfig
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.UserAction
import tec.mx.bancodecomida.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    //Using navigation controller in order to facilitate the work of building the app
    //The navController is the class that manage all fragment interactions
    private lateinit var navController : NavController
    //Using binding library in order to avoid using getElementById
    private lateinit var binding: ActivityMainBinding
    //Navigation Bar
    private lateinit var appBarConfiguration: AppBarConfiguration
    public lateinit var YOUR_CLIENT_ID: String

    private lateinit var text_changeLanguage: TextView
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        //Declare the navHostFragment that has the destinations of our navigation component
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        //Declaring the bottom navigation
        //IMPORTANT: We pass the amount of fragments we choose, in this
        //           case we pass the screens: feed, map, milestones and settings
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.feedFragment, R.id.mapaFragment, R.id.milestonesFragment, R.id.settingsFragment)
        )

        //We're enabling the support of the action bar that it will be transformed
        // into nav bar
        setSupportActionBar(binding.toolbar)
        //This method used to manage the navigation drawer icon and toolbar too.
        setupActionBarWithNavController(navController, appBarConfiguration)
        //The toolbar will be updated when the destination changes
        binding.bottomNav.setupWithNavController(navController)

        //Paypal config
        YOUR_CLIENT_ID="AcCy607igEQA6IOd5OOp7rP7vZzFzgToV919o4kCPQ6K8TulbjY1XgA8zx8CLmvRRbWZW02F8E1iG5y5"
        val config = CheckoutConfig(
            application = application,
            clientId = YOUR_CLIENT_ID,
            environment = Environment.SANDBOX,
            returnUrl = "tec.mx.bancodecomida://paypalpay",
            currencyCode = CurrencyCode.MXN,
            userAction = UserAction.PAY_NOW,
            settingsConfig = SettingsConfig(
                loggingEnabled = true
            )
        )
        PayPalCheckout.setConfig(config)

        // Language config
        LoadLocale();

        text_changeLanguage = findViewById(R.id.text_changeLanguage)
        text_changeLanguage.setOnClickListener(View.OnClickListener {
            openDialogForLanguageChange();
        })

    }

    private fun openDialogForLanguageChange() {
        val list = arrayOf("English", "Español")
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Seleccionar idioma")
        alertDialog.setSingleChoiceItems(list, -1, DialogInterface.OnClickListener { dialog, i ->
            if(i == 0) {
                setLocale("en")
                recreate()
            } else if(i == 1) {
                setLocale("es")
                recreate()
            }
        })

        alertDialog.setNeutralButton("Cancelar", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })

        val mDialog = alertDialog.create()
        mDialog.show()
    }

    private fun setLocale(language: String) {
        val local = Locale(language)
        Locale.setDefault(local)
        val config = Configuration()
        config.locale = local;

        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        editor = getSharedPreferences(" ", Context.MODE_PRIVATE).edit()
        editor.putString("idioma_seleccionado", language)
        editor.apply()
    }

    private fun LoadLocale() {
        sharedPreferences = getSharedPreferences("Ajustes", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("idioma_seleccionado", " ")

        if (language != null) {
            setLocale(language)
        }
    }

    //We're connecting AppCompatActivity with NavigationUI, this means
    // that we're connecting the top part of our app (title, hamburger menu, etc)
    // with the navigator, so it's just like magic!
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {

    }
}