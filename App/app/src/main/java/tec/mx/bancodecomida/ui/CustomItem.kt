
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tec.mx.bancodecomida.R
import tec.mx.bancodecomida.model.New
import tec.mx.bancodecomida.model.NewsImage
import tec.mx.bancodecomida.model.NewsImage2
import tec.mx.bancodecomida.repository.NewsList


@Composable
fun newsListItem(new: New, selectedItem: (New) -> Unit) {


            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                elevation = 5.dp,
                shape = RoundedCornerShape(corner = CornerSize(10.dp))
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()

                        .clickable { selectedItem(new) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NewsImage(new = new)
                    Column {
                        Text(text = new.title, style = MaterialTheme.typography.h5,fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(20.dp))
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
fun newsListImage(new: New, selectedItem: (New) -> Unit) {
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
        text = "\n\n  Breaking News",
        modifier = Modifier
            .height(250.dp),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold,



        )

    Text(
        text = "\n\n\n\n\n\n\n\n\n\n\n\n  Most Popular",
        modifier = Modifier
            .height(250.dp),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold,



    )
    val new = remember { NewsList.new }
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
                    items = new,
                    itemContent = {
                        newsListImage(new = it, selectedItem)
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
                        newsListItem(new = it, selectedItem)
                    }
                )
            }
        }
    }

    }


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
                            .clip(shape = RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = new.title,
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.Bold
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
                        text = "IMDB : ${new.author}",
                        style = MaterialTheme.typography.h5,
                        fontStyle = FontStyle.Italic
                    )

                }
            }
        }





@Composable
fun DisplayImageNews(selectedItem: (New) -> Unit) {

    val new = remember { NewsList.new }
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = new,
            itemContent = {
                newsListImage(new = it, selectedItem)
            }
        )
    }

}






