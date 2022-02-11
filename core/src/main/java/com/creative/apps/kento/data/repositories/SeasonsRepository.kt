package com.creative.apps.kento.data.repositories

import com.creative.apps.kento.data.datasources.SeasonsDataSource

class SeasonsRepository(private val datasource: SeasonsDataSource) {
    suspend fun getSeasons() = datasource.get()
}