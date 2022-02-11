package com.creative.apps.kento.framework.network

import com.creative.apps.kento.domain.remote.GenericResponse
import com.creative.apps.kento.domain.remote.anime.Anime
import com.creative.apps.kento.domain.remote.anime.Characters
import com.creative.apps.kento.domain.remote.characters.CharacterInformation
import com.creative.apps.kento.domain.remote.genres.Genre
import com.creative.apps.kento.domain.remote.seasons.Season
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimesServices {

    @GET("top/anime")
    suspend fun getAnimes(@Query("page") page : Int) : Response<GenericResponse<List<Anime>>>

    @GET("anime")
    suspend fun getAnimesByGenre(@Path("genres") id : Int) : Response<GenericResponse<List<Anime>>>

    @GET("genres/anime")
    suspend fun getAnimeGenres() : Response<GenericResponse<List<Genre>>>

    @GET("seasons")
    suspend fun getSeasons() : Response<GenericResponse<List<Season>>>

    @GET("seasons/{year}/{season}")
    suspend fun getAnimesBySeason(@Path("year") year : Int, @Path("season") season : String) : Response<GenericResponse<List<Anime>>>

    @GET("anime/{id}")
    suspend fun getAnimeInformation(@Path("id") id : Int) : Response<GenericResponse<Anime>>

    @GET("anime/{id}/characters")
    suspend fun getAnimeCharacters(@Path("id") id : Int) : Response<GenericResponse<List<Characters>>>

    @GET("characters/{id}")
    suspend fun getCharacterInformation(@Path("id") id: Int) : Response<GenericResponse<CharacterInformation>>
}