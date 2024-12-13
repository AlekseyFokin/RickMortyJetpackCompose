package org.sniffsnirr.rv_rick_morty.repository

import org.sniffsnirr.rickandmortyonjpc.repository.model.characters.Characters
import org.sniffsnirr.rickandmortyonjpc.repository.model.episodes.Episode
import org.sniffsnirr.rickandmortyonjpc.repository.model.locations.Locations
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("/api/character")
    suspend fun getCharacters( @Query("page") page: Int): Characters //характеристики героев

    @GET("/api/location")
    suspend fun getLocations( @Query("page") page: Int): Locations// локации

    @GET("/api/episode/{ids}")
    suspend fun getEpisodes(@Path("ids") ids:String):List<Episode>// список эпизодов по id
}