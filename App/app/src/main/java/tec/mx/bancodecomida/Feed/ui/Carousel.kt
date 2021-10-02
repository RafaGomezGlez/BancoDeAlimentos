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
import tec.mx.bancodecomida.R

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
            NewsImageBig(New(1,"Testing","Erick","LOREMDOASKDOASKD", R.drawable.maxresdefault,2021))
            NewsImageBig(New(2,"Testing","Erick","LOREMDOASKDOASKD", R.drawable.maxresdefault,2021))
        }

    }
}

