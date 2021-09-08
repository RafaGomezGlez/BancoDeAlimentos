
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tec.mx.bancodecomida.model.New

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
}

@Composable
fun ScrollableColumnDemo(new: New) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        for (i in 1..100) {
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
