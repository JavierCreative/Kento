package com.creative.apps.kento.framework.sources

import com.creative.apps.kento.data.datasources.GenresDataSource
import com.creative.apps.kento.domain.remote.genres.Genre
import com.creative.apps.kento.exception.ServiceErrorException
import com.creative.apps.kento.framework.network.AnimesServices
import java.net.SocketTimeoutException
import javax.inject.Inject

class RemoteGenresDataSource
    @Inject constructor(private val services: AnimesServices) : GenresDataSource {

    override suspend fun get(): List<Genre>? {

        val call = services.getAnimeGenres()
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