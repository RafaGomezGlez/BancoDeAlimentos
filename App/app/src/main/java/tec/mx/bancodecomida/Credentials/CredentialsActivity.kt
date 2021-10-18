package tec.mx.bancodecomida.Credentials

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import tec.mx.bancodecomida.MainActivity
import java.util.*
import android.view.Window

import androidx.core.content.ContextCompat

import android.view.WindowManager




private lateinit var auth: FirebaseAuth


class CredentialsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(tec.mx.bancodecomida.R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(tec.mx.bancodecomida.R.layout.activity_credentials)

        //Change color of actionbar
        val actionBar : ActionBar = supportActionBar!!
        val colorDrawable = ColorDrawable(Color.parseColor("#ce0e2d"))
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable)

        //Change color of statusbar
        val window: Window = this.getWindow()
        window.statusBarColor = Color.parseColor("#ce0e2d")


        // Initialize Firebase Auth
        auth = Firebase.auth
        val currentUser = auth.currentUser

        if (currentUser != null){
            val activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }
    }}

