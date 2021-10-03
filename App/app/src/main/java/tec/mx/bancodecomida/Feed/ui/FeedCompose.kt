package tec.mx.bancodecomida.Feed.ui
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.firebase.firestore.Query
import com.jet.firestore.JetFirestore
import com.jet.firestore.getListOfObjects
import tec.mx.bancodecomida.Feed.model.New


@Composable
fun DisplayNews(selectedItem: (New) -> Unit) {

    var newList by remember { mutableStateOf(listOf<New>()) }

    JetFirestore(
        path = { collection("New") },
        queryOnCollection = { orderBy("id", Query.Direction.DESCENDING) },
        onRealtimeCollectionFetch = { values, _ ->
            newList = values.getListOfObjects()
        }
    ){
        ConstraintLayout {
            // Create references for the composables to constrain
            val (box1,box2, text, text1) = createRefs()
            // Assign reference "text" to the Text composable
            // and constrain it to the bottom of the Button composable
            Text(
                text = "Breaking News",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .constrainAs(text) {
                        top.linkTo(parent.top, margin = 65.dp)
                    }
            )

            val new = newList
            Log.d("FIRESTORE-new", new.toString())
            val new2 = newList
            Box(
                //modifier = Modifier.fillMaxWidth(),
                modifier =  Modifier
                    .constrainAs(box1){
                        top.linkTo(text.bottom, margin = 0.dp )
                    },
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
                                Carousel().Carousel(new = it, selectedItem = selectedItem )
                            }
                        )
                    }
                }
            }
            Text(
                text = "Most Popular",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .constrainAs(text1) {
                        bottom.linkTo(box1.bottom, margin = 225.dp)
                    })
            Spacer(modifier = Modifier.height(300.dp))
            Box(
                modifier =  Modifier.constrainAs(box2){
                    top.linkTo(text1.bottom, margin = 10.dp )
                },
                contentAlignment = Alignment.Center
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
                                ListItem().NewsListItem(new = it, selectedItem = selectedItem)
                            }
                        )
                    }
                }
            }
        }
    }

}


