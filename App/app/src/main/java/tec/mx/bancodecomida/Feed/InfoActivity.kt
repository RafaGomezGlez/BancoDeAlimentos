package tec.mx.bancodecomida.Feed

import ViewMoreInfo
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import tec.mx.bancodecomida.Feed.model.New

class InfoActivity : AppCompatActivity() {


    companion object{
        private const val newsId = "newid"
        fun intent(context: Context, new: New)=
            Intent(context, InfoActivity::class.java).apply {
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