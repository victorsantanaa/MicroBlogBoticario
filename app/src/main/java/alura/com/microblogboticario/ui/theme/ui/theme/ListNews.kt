package alura.com.microblogboticario.ui.theme.ui.theme

import alura.com.microblogboticario.model.News
import alura.com.microblogboticario.model.NewsModel
import alura.com.microblogboticario.ui.theme.ItemList
import alura.com.microblogboticario.viewmodel.NewsViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun NewsList(
    news: List<News>,
    onItemClicked: (news: NewsModel) -> Unit,
    navController: NavController
) {

    val listState = rememberLazyListState()


    @OptIn(ExperimentalFoundationApi::class)
    LazyColumn(state = listState) {

        stickyHeader {
            NewsListHeader()
        }

        itemsIndexed(news) {index, item ->
            ItemList(
                news = item,
                onItemClicked = onItemClicked,
                navController = navController
            )
        }

    }
}

@Composable
private fun NewsListHeader() {
    Surface(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        Text(
            text = "Boticario News",
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
            )
    }
}

@Composable
fun NewsScreen(
    navController: NavController,
    newsViewModel: NewsViewModel
) {
    Surface(color = MaterialTheme.colors.background) {
        NewsList(
            news = newsViewModel.getAllNewsFromDatabase(),
            onItemClicked = newsViewModel::onItemClicked,
            navController = navController
        )
    }
}