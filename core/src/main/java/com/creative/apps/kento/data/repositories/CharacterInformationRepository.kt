package com.creative.apps.kento.data.repositories

import com.creative.apps.kento.data.datasources.CharacterInformationDataSource

class CharacterInformationRepository(private val datasource : CharacterInformationDataSource) {
    suspend fun getCharacterInformation(id : Int) = datasource.get(id)
}