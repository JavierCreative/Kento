package com.creative.apps.kento.framework.sources

import com.creative.apps.kento.data.datasources.AnimeCharactersDataSource
import com.creative.apps.kento.domain.remote.anime.Characters
import com.creative.apps.kento.domain.remote.characters.Character
import com.creative.apps.kento.exception.ServiceErrorException
import com.creative.apps.kento.framework.network.AnimesServices
import java.net.SocketTimeoutException
import javax.inject.Inject

class RemoteAnimeCharactersDataSource @Inject constructor(private val services: AnimesServices) : AnimeCharactersDataSource {

    override suspend fun get(id: Int): List<Characters>? {
        try {
            val call = services.getAnimeCharacters(id)
            val response = call.body()
            if (call.isSuccessful) {
                return response?.data
            } else {
                throw ServiceErrorException("Error", 500)
            }
        } catch (exception : SocketTimeoutException) {
            throw ServiceErrorException("Error", 503)
        }
    }

}