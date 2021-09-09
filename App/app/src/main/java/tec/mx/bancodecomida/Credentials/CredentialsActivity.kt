package tec.mx.bancodecomida.Credentials

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import tec.mx.bancodecomida.MainActivity
import tec.mx.bancodecomida.R

private lateinit var auth: FirebaseAuth


class CredentialsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credentials)


        // Initialize Firebase Auth
        auth = Firebase.auth

        val currentUser = auth.currentUser

        if (currentUser != null){
            val activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }

    }

}
