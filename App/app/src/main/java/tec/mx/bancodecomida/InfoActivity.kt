package tec.mx.bancodecomida

import ViewMoreInfo
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import tec.mx.bancodecomida.model.New

class InfoActivity : AppCompatActivity() {


    companion object{
        private const val newsId = "newid"
        fun intent(context: Context, new: New)=
            Intent(context,InfoActivity::class.java).apply {
                putExtra(newsId, new)
            }
    }

    private val new : New by lazy {
        intent?.getSerializableExtra(newsId) as New
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewMoreInfo(new = new)
        }
    }
}