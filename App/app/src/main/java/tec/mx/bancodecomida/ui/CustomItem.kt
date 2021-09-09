
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tec.mx.bancodecomida.R
import tec.mx.bancodecomida.model.New
import tec.mx.bancodecomida.model.NewsImage
import tec.mx.bancodecomida.repository.NewsList


@Composable
fun newsListItem(new: New, selectedItem: (New) -> Unit) {


            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                elevation = 10.dp,
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
                        Text(text = new.title, style = MaterialTheme.typography.h5)
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = new.author,
                            style = MaterialTheme.typography.body1,
                            maxLines = 3,
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = new.year.toString(), style = MaterialTheme.typography.subtitle1)
                        }

                    }

                }

            }
        }


@Composable
fun DisplayNews(selectedItem: (New) -> Unit) {
    Text(
        text = "Breaking News",
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.h5,
    )
    val new = remember { NewsList.new }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter

    ){
        Box(
            modifier = Modifier
                .height(300.dp)

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
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = new.title,
                        style = MaterialTheme.typography.h3
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
                        style = MaterialTheme.typography.h5
                    )

                }
            }
        }









