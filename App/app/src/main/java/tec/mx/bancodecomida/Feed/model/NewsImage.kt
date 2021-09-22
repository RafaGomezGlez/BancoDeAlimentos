package tec.mx.bancodecomida.Feed.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tec.mx.bancodecomida.R


@Composable
fun NewsImage(new: New) {
    Image(
        painter = painterResource(id = new.imageId),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .padding(4.dp)
            .height(50.dp)
            .width(50.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))

    )
}

@Preview
@Composable
fun NewsImagePreview(){
    NewsImage(
        New(1,"Testing","Erick","LOREMDOASKDOASKD", R.drawable.maxresdefault,2021))
}

@Preview
@Composable
fun NewsImage2Preview(){
NewsImage2(
    New(1,"Testing","Erick","LOREMDOASKDOASKD", R.drawable.maxresdefault,2021))
}

@Composable
fun NewsImage2(new: New) {
    Image(
        painter = painterResource(id = new.imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(5.dp)
            .height(220.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))


    )
}