package org.sniffsnirr.rickandmortyonjpc

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sniffsnirr.rickandmortyonjpc.repository.model.episodes.Episode
import org.sniffsnirr.rickandmortyonjpc.repository.model.locations.Location
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.CharactersPagingSource
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.LocationsPagingSource
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.model.Character
import org.sniffsnirr.rickandmortyonjpc.repository.Repository


class ApiViewModel : ViewModel() {
    val repository = Repository

    val pagesCharacters: Flow<PagingData<Character>> = Pager( // постраничная загрузка героев
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { CharactersPagingSource(repository) }
    ).flow.cachedIn(viewModelScope)

    val pagesLocations: Flow<PagingData<Location>> = Pager(//постраничная загрузка локаций
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { LocationsPagingSource(repository) }
    ).flow.cachedIn(viewModelScope)


    private val _characterFromCache = MutableStateFlow<Character?>(null)// информация о герое на отдельный экран
    val characterFromCache = _characterFromCache.asStateFlow()

    private val _episodes = MutableStateFlow<List<Episode>?>(null)// информация по эпизодам для отельного экрана с героем
    val episodes = _episodes.asStateFlow()

    fun loadCharacterFromCache(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {// загрузка по герою
            kotlin.runCatching {
                repository.getCharacterFromCache(id)
            }.fold(
                onSuccess = { _characterFromCache.value = it },
                onFailure = { Log.d("Проблема с загрузкой выбранного героя", it.message ?: "") }
            )
        }
    }

    fun loadEpisodes(ids: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.getEpisidesByIds(ids)// загрузка по всем эпизодам героя
            }.fold(
                onSuccess = { _episodes.value = it },
                onFailure = { Log.d("Проблема с загрузкой эпизодов", it.message ?: "") }
            )
        }
    }

}