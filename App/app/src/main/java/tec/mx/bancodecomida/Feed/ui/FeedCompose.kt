package tec.mx.bancodecomida.Feed.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.firebase.firestore.Query
import com.jet.firestore.JetFirestore
import com.jet.firestore.getListOfObjects
import tec.mx.bancodecomida.Feed.model.New
import tec.mx.bancodecomida.R


@Composable
fun DisplayNews(selectedItem: (New) -> Unit) {
    var newList by remember { mutableStateOf(listOf<New>()) }
    var newList2 by remember { mutableStateOf(listOf<New>()) }
    //Firestore call in order to fill all data
    JetFirestore(
        path = { collection("New") },
        queryOnCollection = { orderBy("id", Query.Direction.DESCENDING) },
        onRealtimeCollectionFetch = { values, _ ->
            newList = values.getListOfObjects()
        }
    )
    {
        JetFirestore(
            path = { collection("New2") },
            queryOnCollection = { orderBy("id", Query.Direction.DESCENDING) },
            onRealtimeCollectionFetch = { values, _ ->
                newList2 = values.getListOfObjects()
            }
        )
        {

        ConstraintLayout {
                // Create references for the composables to constrain
                val (box1,box2, text, text1) = createRefs()
                val new = newList
                val new2 = newList2

            Text(
                stringResource(R.string.recent_news),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .constrainAs(text) {
                        top.linkTo(parent.top, margin = 65.dp)
                    }
            )
            Box(
                modifier =  Modifier
                    .constrainAs(box1){
                        top.linkTo(text.bottom, margin = 0.dp )
                    }
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(0.dp),
                    ) {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 2.dp),
                    ) {
                        items(
                            items = new2,
                            itemContent = {
                                Carousel().Carousel(new = it, selectedItem = selectedItem )
                            }
                        )
                    }
                }

            Text(
                stringResource(R.string.most_popular),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .constrainAs(text1) {
                        top.linkTo(box1.bottom, margin = 0.dp)
                    })
            Box(
                modifier =  Modifier
                    .fillMaxWidth()
                    .constrainAs(box2){
                    bottom.linkTo(parent.bottom, margin = 48.dp )
                        top.linkTo(text1.bottom, margin = 0.dp)
                    height = (Dimension.fillToConstraints)
                        },
                    contentAlignment = Alignment.Center
                ){
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 1.dp),
                        modifier = Modifier
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


