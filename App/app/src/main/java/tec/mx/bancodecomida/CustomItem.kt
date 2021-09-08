
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tec.mx.bancodecomida.model.New
import tec.mx.bancodecomida.model.NewsImage
import tec.mx.bancodecomida.repository.NewsList

@Composable
fun CustomItem(new: New) {
    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "${new.title}",
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = new.author,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = new.description,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        )
    }
}

/*
@Composable
@Preview
fun CustomItemPreview() {
    CustomItem(
        new = New(
            id = 0,
            title = "John",
            author = "Doe",
            description = "Doe"
        )
    )
}*/

@Composable
fun ScrollableColumnDemo(new: New) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        for (i in 1..20) {
            Text(
                text = "${new.title}",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = new.author,
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = new.description,
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}



@Composable
fun LazyColumnDemo(new: New) {
    LazyColumn {
        items(100) {
            Text(
                text = "${new.title}",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = new.author,
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = new.description,
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )
            Divider(color = Color.Black, thickness = 5.dp)
        }
    }
}

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

    val new = remember { NewsList.new }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = new,
            itemContent = {
                newsListItem(new = it, selectedItem)
            }
        )
    }

}
