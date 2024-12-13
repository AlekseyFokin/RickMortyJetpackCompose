package org.sniffsnirr.rickandmortyonjpc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import org.sniffsnirr.rickandmortyonjpc.ui.paginglocations.ItemLocationView
import org.sniffsnirr.rickandmortyonjpc.ui.theme.DeadColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.ProgressColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.RecyclerviewBackground
import org.sniffsnirr.rickandmortyonjpc.ApiViewModel

@Composable
fun ListLocationsView(viewModel: ApiViewModel) {
    val locationsData = viewModel.pagesLocations.collectAsLazyPagingItems()// постраничная отображение и загрузка локаций
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 56.dp)
            .background(RecyclerviewBackground)
    ) {
        items(
            count = locationsData.itemCount,
            key = locationsData.itemKey { it.id }) { index ->
            val location = locationsData[index]
            if (location != null) {
                ItemLocationView(location)
            }
        }
        when {// обработка состояний
            locationsData.loadState.append is LoadState.Loading -> {
                item {
                    Box(modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(top = 26.dp)) {
                        CircularProgressIndicator(
                            color = ProgressColor,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            locationsData.loadState.append is LoadState.Error -> {
                item {
                    Row(modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(16.dp)) {
                        Button(onClick = { locationsData.retry() }) { Text(text = "Повторить загрузку") }
                        Text(
                            color = DeadColor,
                            text = "Ошибка загрузки",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            locationsData.loadState.refresh is LoadState.Loading -> {
                item {
                    Box(modifier = Modifier.fillParentMaxSize()) {
                        CircularProgressIndicator(
                            color = ProgressColor,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            locationsData.loadState.refresh is LoadState.Error -> {
                item {
                    Row(modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(16.dp)) {
                        Button(onClick = { locationsData.retry() }) { Text(text = "Повторить загрузку") }
                        Text(
                            color = DeadColor,
                            text = "Ошибка загрузки",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }

    }
}