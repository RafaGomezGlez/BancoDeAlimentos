package tec.mx.bancodecomida.Feed.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tec.mx.bancodecomida.Feed.model.New

class Carousel {
    @Composable
    fun Carousel(new: New, selectedItem: (New) -> Unit) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable { selectedItem(new) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            NewsImageBig(new = new)
        }
    }

    @Preview
    @Composable
    fun CarouselPreview(){
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NewsImageBig(New(1,"Testing","Erick","LOREMDOASKDOASKD","https://firebasestorage.googleapis.com/v0/b/bancodealimentos-2f99b.appspot.com/o/presentation_background.png?alt=media&token=d4a4ba7e-ebd1-4ef4-9204-12c760fd0933",2021))
            NewsImageBig(New(2,"Testing","Erick","LOREMDOASKDOASKD", "https://firebasestorage.googleapis.com/v0/b/bancodealimentos-2f99b.appspot.com/o/presentation_background.png?alt=media&token=d4a4ba7e-ebd1-4ef4-9204-12c760fd0933",2021))
        }

    }
}

