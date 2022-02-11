package com.creative.apps.kento.framework.sources

import com.creative.apps.kento.data.datasources.PopularAnimesDataSource
import com.creative.apps.kento.domain.remote.anime.Anime
import com.creative.apps.kento.exception.ServiceErrorException
import com.creative.apps.kento.framework.network.AnimesServices
import java.net.SocketTimeoutException
import javax.inject.Inject

class RemotePopularAnimesDataSource
    @Inject constructor(private val services : AnimesServices) : PopularAnimesDataSource {

    override suspend fun get(page: Int): List<Anime>? {

        val call = services.getAnimes(page)
        val response = call.body()
        try {
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