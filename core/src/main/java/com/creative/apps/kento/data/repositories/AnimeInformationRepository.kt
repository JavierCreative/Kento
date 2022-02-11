package com.creative.apps.kento.data.repositories

import com.creative.apps.kento.data.datasources.AnimeInformationDataSource

class AnimeInformationRepository(private val dataSource : AnimeInformationDataSource) {
    suspend fun getAnimeInformation(id : Int) = dataSource.get(id)
}