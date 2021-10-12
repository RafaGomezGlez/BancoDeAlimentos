package tec.mx.bancodecomida.Feed.ui

import android.graphics.Paint
import android.text.Layout
import android.util.Log
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import tec.mx.bancodecomida.Feed.model.New
import tec.mx.bancodecomida.R
import java.lang.Float.min

class ClickableNew {
    @Composable
    fun NewContent(new: New) {
        val scrollState = rememberScrollState()
        Log.d("ImageId", new.imageId)
        ConstraintLayout {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                GlideImage(
                    imageModel = new.imageId,
                    circularReveal = CircularReveal(duration = 300),
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                        alpha = min(1f, 1 - (scrollState.value / 600f))
                        translationY = -scrollState.value * 0.1f
                    },
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = new.title,
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 0.dp)
                    )
                    Text(
                        text = "Author ${new.author}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .padding(
                                start = 20.dp,
                                bottom = 0.dp
                            )
                    )
                    Text(
                        text = new.description,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(
                                top = 15.dp,
                                start = 20.dp,
                                end = 20.dp,
                                bottom = 15.dp
                            )
                    )
                    Text(
                        text = "${new.year}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .padding(
                                end = 20.dp,
                                bottom = 30.dp
                            )
                            .fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                }
            }

        }
    }


    @Preview
    @Composable
    fun NewContentPreview() {
        val scrollState = rememberScrollState()
        ConstraintLayout {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.comida),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            alpha = min(1f, 1 - (scrollState.value / 600f))
                            translationY = -scrollState.value * 0.1f
                        },
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = "Title",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 0.dp)
                    )
                    Text(
                        text = "Author",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .padding(
                                start = 20.dp,
                                bottom = 0.dp
                            )
                    )
                    Text(
                        text = "Lorem ipsum dolor sit amet consectetur adipiscing elit hendrerit iaculis, viverra maecenas in eget commodo blandit cubilia arcu. Faucibus vivamus sapien tellus ullamcorper volutpat lacus litora bibendum, tempus per fusce mus aliquet lobortis natoque, enim nam egestas leo felis ligula tortor. Rhoncus in senectus tincidunt nullam eros taciti vitae class, eget netus donec sed viverra pharetra velit non pellentesque, suspendisse quam hac ornare consequat lacinia ultrices.\n" +
                                "\n" +
                                "Egestas et condimentum ante iaculis tincidunt montes eu commodo lobortis, aptent lectus cum faucibus magnis sem litora at nec, phasellus imperdiet varius aliquet ultricies massa vitae nisl. Nunc feugiat quisque proin euismod vel inceptos dapibus dictumst fusce ultricies, fringilla et mauris senectus volutpat integer hendrerit convallis sagittis. Nisi mattis potenti egestas platea feugiat arcu blandit, nascetur natoque maecenas risus erat et habitant quisque, torquent habitasse suspendisse mollis imperdiet velit.",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(
                                top = 15.dp,
                                start = 20.dp,
                                end = 20.dp,
                                bottom = 15.dp
                            )
                    )
                    Text(
                        text = "${2021}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .padding(
                                end = 20.dp,
                                bottom = 30.dp
                            )
                            .fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                }
            }

        }
    }
}