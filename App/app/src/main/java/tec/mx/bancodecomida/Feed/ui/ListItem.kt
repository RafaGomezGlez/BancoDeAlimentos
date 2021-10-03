package tec.mx.bancodecomida.Feed.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tec.mx.bancodecomida.Feed.model.New

class ListItem {
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
                NewsImageSmall(new = new )
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

    @Preview
    @Composable
    fun NewsListItemPreview() {
        New(1,"Testing","Erick","LOREMDOASKDOASKD", "https://firebasestorage.googleapis.com/v0/b/bancodealimentos-2f99b.appspot.com/o/presentation_background.png?alt=media&token=d4a4ba7e-ebd1-4ef4-9204-12c760fd0933",2021)

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
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NewsImageSmall(new = New(1,"Testing","Erick","LOREMDOASKDOASKD","https://firebasestorage.googleapis.com/v0/b/bancodealimentos-2f99b.appspot.com/o/presentation_background.png?alt=media&token=d4a4ba7e-ebd1-4ef4-9204-12c760fd0933",2021) )
                Column (
                    modifier = Modifier
                        .padding(start = 5.dp)
                ){
                    Text(text = "Testing",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(0.dp))
                    Text(
                        text = "Erick",
                        style = MaterialTheme.typography.body1,
                        maxLines = 3,
                        fontStyle = FontStyle.Italic
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = 2021.toString(), style = MaterialTheme.typography.caption)
                    }

                }

            }

        }
    }
}