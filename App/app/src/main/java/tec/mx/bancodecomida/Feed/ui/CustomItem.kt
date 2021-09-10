
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import tec.mx.bancodecomida.Feed.model.New
import tec.mx.bancodecomida.Feed.model.NewsImage
import tec.mx.bancodecomida.Feed.model.NewsImage2
import tec.mx.bancodecomida.Feed.repository.NewsList
import tec.mx.bancodecomida.Feed.repository.NewsList2
import java.lang.Float.min

@Composable
internal fun NewsListItem(new: New, selectedItem: (New) -> Unit) {


            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                elevation = 3.dp,
                shape = RoundedCornerShape(corner = CornerSize(10.dp))
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()

                        .clickable { selectedItem(new) }
                        ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NewsImage(new = new )
                    Column (
                        modifier = Modifier
                        .padding(start = 5.dp)
                    ){

                        Text(text = new.title,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(0.dp))
                        Text(
                            text = new.author,
                            style = MaterialTheme.typography.body1,
                            maxLines = 3,
                            fontStyle = FontStyle.Italic
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = new.year.toString(), style = MaterialTheme.typography.caption)
                        }

                    }

                }

            }
        }
@Composable
fun NewsListImage(new: New, selectedItem: (New) -> Unit) {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable { selectedItem(new) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        NewsImage2(new = new)


    }

}

@Composable
fun DisplayNews(selectedItem: (New) -> Unit) {
    Text(
        text = "Breaking News",
        modifier = Modifier
            .padding(start = 10.dp,top = 60.dp),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold,



        )

    Text(
        text = "Most Popular",
        modifier = Modifier
            .padding(start = 10.dp,top = 325.dp),

        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold,

    )
    val new = remember { NewsList.new }
    val new2 = remember { NewsList2.new }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .height(500.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp)
            ) {
                items(
                    items = new2,
                    itemContent = {
                        NewsListImage(new = it, selectedItem)
                    }
                )
        }
    }
    }


    Spacer(modifier = Modifier.height(300.dp))
    Box(
        modifier = Modifier.fillMaxWidth(),

        contentAlignment = Alignment.BottomCenter


    ){
        Box(
            modifier = Modifier
                .height(290.dp)
                .clip(RoundedCornerShape(10.dp))

        ){
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp)
            ) {
                items(
                    items = new,
                    itemContent = {
                        NewsListItem(new = it, selectedItem)
                    }
                )
            }
        }
    }

    }


//ESTA EN NEW 2
@Composable
fun ViewMoreInfo(new: New) {
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
                                alpha = min(2f, 2 - (scrollState.value / 700f))
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



