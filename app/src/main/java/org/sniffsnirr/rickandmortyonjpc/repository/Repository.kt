package org.sniffsnirr.rickandmortyonjpc.repository

import kotlinx.coroutines.delay
import org.sniffsnirr.rickandmortyonjpc.repository.model.characters.Characters
import org.sniffsnirr.rickandmortyonjpc.repository.model.characters.Result
import org.sniffsnirr.rickandmortyonjpc.repository.model.episodes.Episode
import org.sniffsnirr.rickandmortyonjpc.repository.model.locations.Locations
import org.sniffsnirr.rickandmortyonjpc.repository.model.locations.LocationsInfo
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.model.Character
import org.sniffsnirr.rv_rick_morty.repository.RetrofitInstance

object Repository {
    private val getRickAndMortyApi = RetrofitInstance.getApi()
    private val cache = mutableMapOf<Int, Character>()
    private val castToListCharacter = CastToListCharacter()

    suspend fun getCharacters(page: Int = 0): List<Result> {
        val characters: Characters?
        characters = getRickAndMortyApi.getCharacters(page)

        castToListCharacter.castListResultToListCharacter(characters.results)
            .map { character ->//кэширую, так как на отдельном экране по герою таже информация что уже загружена для списка героев
                cache.put(character.id, character)
            }

        return characters.results
    }

    suspend fun getLocations(page: Int = 0): List<LocationsInfo> {
        val locations: Locations?
        locations = getRickAndMortyApi.getLocations(page)
        delay(1000)
        return locations.results
    }

    fun getCharacterFromCache(id: Int): Character? {
        return cache[id]
    }

    suspend fun getEpisidesByIds(listId: List<Int>): List<Episode> {
        return getRickAndMortyApi.getEpisodes(listId.joinToString())
    }
}