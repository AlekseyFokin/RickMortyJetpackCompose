package org.sniffsnirr.rickandmortyonjpc.repository

import org.sniffsnirr.rickandmortyonjpc.repository.model.characters.Result
import org.sniffsnirr.rickandmortyonjpc.ui.pagingcharacters.model.Character

class CastToListCharacter {
     fun castListResultToListCharacter(listResult: List<Result>?): List<Character> {
        val characterList = mutableListOf<Character>()
        listResult?.map { result ->
            val character = Character(
                result.id,
                result.image,
                result.location,
                result.name,
                result.species,
                result.status,
                result.gender,
                result.episode.map{episode->(episode.substring(episode.lastIndexOf("/")+1,episode.length)).toInt()}
            )
            characterList.add(character)
        }
        return characterList
    }
}