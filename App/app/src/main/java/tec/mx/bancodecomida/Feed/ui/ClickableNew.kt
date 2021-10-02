package tec.mx.bancodecomida.Feed.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tec.mx.bancodecomida.Feed.model.New
import tec.mx.bancodecomida.R
import java.lang.Float

class ClickableNew {
    //ESTA EN NEW 2
    @Composable
    fun newContent(new: New) {
        val scrollState = rememberScrollState()

        Card(
            modifier = Modifier.padding(10.dp),
            elevation = 10.dp,
            shape = RoundedCornerShape(corner = CornerSize(10.dp))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = new.imageId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(shape = RoundedCornerShape(4.dp))
                        .graphicsLayer {
                            alpha = Float.min(2f, 2 - (scrollState.value / 700f))
                            translationY = -scrollState.value * 0.1f
                        },
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = new.title,
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,

                    )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = new.description,
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Original release : ${new.year}",
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Author : ${new.author}",
                    style = MaterialTheme.typography.h5,
                    fontStyle = FontStyle.Italic
                )

            }
        }
    }

    @Preview
    @Composable
    fun newContentPreview() {
        val scrollState = rememberScrollState()

        Card(
            modifier = Modifier.padding(10.dp),
            elevation = 10.dp,
            shape = RoundedCornerShape(corner = CornerSize(10.dp))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.maxresdefault),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(shape = RoundedCornerShape(4.dp))
                        .graphicsLayer {
                            alpha = Float.min(2f, 2 - (scrollState.value / 700f))
                            translationY = -scrollState.value * 0.1f
                        },
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Testing",
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,

                    )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "LOREMDOASKDOASKD",
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Original release : ${2021}",
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Author : ${"Erick"}",
                    style = MaterialTheme.typography.h5,
                    fontStyle = FontStyle.Italic
                )

            }
        }
    }
}