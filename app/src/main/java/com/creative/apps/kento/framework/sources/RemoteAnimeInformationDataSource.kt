package com.creative.apps.kento.framework.sources

import com.creative.apps.kento.data.datasources.AnimeInformationDataSource
import com.creative.apps.kento.domain.remote.anime.Anime
import com.creative.apps.kento.exception.ServiceErrorException
import com.creative.apps.kento.framework.network.AnimesServices
import java.net.SocketTimeoutException
import javax.inject.Inject

class RemoteAnimeInformationDataSource
@Inject constructor(private val services: AnimesServices) : AnimeInformationDataSource{

    override suspend fun get(id: Int): Anime? {
        try {
            val call = services.getAnimeInformation(id)
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