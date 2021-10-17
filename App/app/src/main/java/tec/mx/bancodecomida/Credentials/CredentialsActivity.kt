package tec.mx.bancodecomida.Credentials

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import tec.mx.bancodecomida.MainActivity
import tec.mx.bancodecomida.R
import java.util.*

private lateinit var auth: FirebaseAuth

/*// Change language
private lateinit var text_changeLanguage: TextView
private lateinit var editor: SharedPreferences.Editor
private lateinit var sharedPreferences: SharedPreferences*/


class CredentialsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credentials)


        // Initialize Firebase Auth
        auth = Firebase.auth
        val currentUser = auth.currentUser

        if (currentUser != null){
            val activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }
    /*
        // Language config
        LoadLocale();

        text_changeLanguage = findViewById(R.id.text_changeLanguage)
        text_changeLanguage.setOnClickListener(View.OnClickListener {
            openDialogForLanguageChange();
        })
    */
    }}
    /*
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


*/
