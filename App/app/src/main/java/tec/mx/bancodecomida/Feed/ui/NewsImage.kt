package tec.mx.bancodecomida.Feed.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import tec.mx.bancodecomida.Feed.model.New


@Composable
fun NewsImageSmall(new: New) {
    Image(
        painter = rememberImagePainter(new.imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(4.dp)
            .height(50.dp)
            .width(50.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))

    )
}
@Composable
fun NewsImageBig(new: New) {
    Image(
        painter = rememberImagePainter(new.imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(5.dp)
            .height(220.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))


    )
}

@Preview
@Composable
fun NewsImageSmallPreview(){
    NewsImageSmall(
        New(1,"Testing","Erick","LOREMDOASKDOASKD", "https://firebasestorage.googleapis.com/v0/b/bancodealimentos-2f99b.appspot.com/o/presentation_background.png?alt=media&token=d4a4ba7e-ebd1-4ef4-9204-12c760fd0933",2021)
    )
}

@Preview
@Composable
fun NewsImageBigPreview(){
NewsImageBig(
    New(1,"Testing","Erick","LOREMDOASKDOASKD", "https://firebasestorage.googleapis.com/v0/b/bancodealimentos-2f99b.appspot.com/o/presentation_background.png?alt=media&token=d4a4ba7e-ebd1-4ef4-9204-12c760fd0933",2021)
)
}