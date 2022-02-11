package com.creative.apps.kento.framework.sources

import com.creative.apps.kento.data.datasources.SeasonsDataSource
import com.creative.apps.kento.domain.remote.seasons.Season
import com.creative.apps.kento.exception.ServiceErrorException
import com.creative.apps.kento.framework.network.AnimesServices
import java.net.SocketTimeoutException
import javax.inject.Inject

class RemoteSeasonsDataSource
    @Inject constructor(private val services : AnimesServices) : SeasonsDataSource {

    override suspend fun get(): List<Season>? {

        val call = services.getSeasons()
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