package org.sniffsnirr.rickandmortyonjpc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.ItemCharacterView
import org.sniffsnirr.rickandmortyonjpc.ui.theme.DeadColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.ProgressColor
import org.sniffsnirr.rickandmortyonjpc.ui.theme.RecyclerviewBackground
import org.sniffsnirr.rickandmortyonjpc.ApiViewModel
import org.sniffsnirr.rickandmortyonjpc.ui.ListCharactersViewCompanion.Companion.LIST_OF_CHARACTERS_TEST_TAG

@Composable
fun ListCharactersView(viewModel: ApiViewModel, navController: NavHostController) {

    val charactersData =
        viewModel.pagesCharacters.collectAsLazyPagingItems()// постраничная отображение и загрузка героев
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 16.dp)
            .background(RecyclerviewBackground)
            .testTag(LIST_OF_CHARACTERS_TEST_TAG)
    ) {

        items(
            count = charactersData.itemCount,
            key = charactersData.itemKey { it.id }) { index ->
            val character = charactersData[index]
            if (character != null) {
                ItemCharacterView(character, navController, viewModel,"$index")
            }
        }
        when {// обработка состояний
            charactersData.loadState.append is LoadState.Loading -> { // загрузка-добавление данных в существующий список
                item {
                    Box(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(top = 26.dp)
                    ) {
                        CircularProgressIndicator(
                            color = ProgressColor,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
            }

            charactersData.loadState.append is LoadState.Error -> {// ошибка при добавлении данных
                item {
                    Row(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(16.dp)
                    ) {
                        Button(onClick = { charactersData.retry() }) { Text(text = "Повторить загрузку") }
                        Text(
                            color = DeadColor,
                            text = "Ошибка загрузки",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            charactersData.loadState.refresh is LoadState.Loading -> {// загрузка списка с нуля
                item {
                    Box(modifier = Modifier.fillParentMaxSize()) {
                        CircularProgressIndicator(
                            color = ProgressColor,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            charactersData.loadState.refresh is LoadState.Error -> {// ошибка при загрузке списка с нуля
                item {
                    Row(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(16.dp)
                    ) {
                        Button(onClick = { charactersData.retry() }) { Text(text = "Повторить загрузку") }
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
class ListCharactersViewCompanion(){
    companion object{
        const val LIST_OF_CHARACTERS_TEST_TAG="characterList"
    }
}