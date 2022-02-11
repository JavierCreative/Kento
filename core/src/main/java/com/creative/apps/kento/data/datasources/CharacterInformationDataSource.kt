package com.creative.apps.kento.data.datasources

import com.creative.apps.kento.domain.remote.characters.CharacterInformation

interface CharacterInformationDataSource {
    suspend fun get(id : Int) : CharacterInformation?
}