package tec.mx.bancodecomida

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.os.LocaleList.setDefault
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
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
import tec.mx.bancodecomida.Settings.settings_support_agreements
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
    lateinit var YOUR_CLIENT_ID: String
    //Locale
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences
    // Agreements
    // private val WebView1: Fragment = settings_support_agreements.newInstance("https://bamx.org.mx/privacy-policy/")
    private val WebView1: Fragment = settings_support_agreements.newInstance("file:///android_asset/privacy.html")
    private val WebView2: Fragment = settings_support_agreements.newInstance("file:///android_asset/termsandconditions.html")
    private val fm = supportFragmentManager
    private var visibleWebView1: Fragment = WebView1
    private var visibleWebView2: Fragment = WebView2

    var webViewNum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        //Create the object binding, it's similar to R. ...., whenever you want
        //to call a View in your xml file, just use binding.View        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Change color of statusbar
        val window: Window = this.getWindow()
        window.statusBarColor = Color.parseColor("#ce0e2d")

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

    }

    // Load agreements
    fun loadAgreements1() {
        fm.beginTransaction().add(R.id.frag, WebView1).commit()
        webViewNum = 1
    }

    fun loadAgreements2() {
        fm.beginTransaction().add(R.id.frag, WebView2).commit()
        webViewNum = 2
    }

    fun rmAgreement() {
        if(webViewNum == 1)
            fm.beginTransaction().remove(WebView1).commit()
        else if(webViewNum == 2)
            fm.beginTransaction().remove(WebView2).commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if ((keyCode == KeyEvent.KEYCODE_BACK) && (webViewNum == 1)) {
            val intent = Intent(visibleWebView1.hashCode().toString())
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            true
        } else if((keyCode == KeyEvent.KEYCODE_BACK) && (webViewNum == 2)) {
            val intent = Intent(visibleWebView2.hashCode().toString())
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            true
        } else
            super.onKeyDown(keyCode, event)
    }

    // Language config
    fun openDialogForLanguageChange() {
        val select_lang = resources.getString(R.string.select_lang)
        val cancel_btn = resources.getString(R.string.cancel)
        val list = arrayOf("English", "Español")
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(select_lang)
        alertDialog.setSingleChoiceItems(list, -1) { dialog, i ->
            if (i == 0) {
                setLocale("en")
                recreate()
            } else if (i == 1) {
                setLocale("es")
                recreate()
            }
        }

        alertDialog.setNeutralButton(cancel_btn) { dialog, which ->
            dialog.cancel()
        }

        val mDialog = alertDialog.create()
        mDialog.show()
    }

    private fun setLocale(language: String) {
        val local = Locale(language)
        //Locale.setDefault(local)
        val config = Configuration()
        config.setLocale(local)

        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        editor = getSharedPreferences(" ", Context.MODE_PRIVATE).edit()
        editor.putString("idioma_seleccionado", language)
        editor.apply()
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