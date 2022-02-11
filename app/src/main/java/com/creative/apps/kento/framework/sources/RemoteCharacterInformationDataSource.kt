package com.creative.apps.kento.framework.sources

import com.creative.apps.kento.data.datasources.CharacterInformationDataSource
import com.creative.apps.kento.domain.remote.characters.CharacterInformation
import com.creative.apps.kento.exception.ServiceErrorException
import com.creative.apps.kento.framework.network.AnimesServices
import java.net.SocketTimeoutException
import javax.inject.Inject

class RemoteCharacterInformationDataSource
@Inject constructor(private val services: AnimesServices) : CharacterInformationDataSource{

    override suspend fun get(id: Int): CharacterInformation? {
        try {
            val call = services.getCharacterInformation(id)
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