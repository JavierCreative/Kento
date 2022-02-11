package com.creative.apps.kento.data.datasources

import com.creative.apps.kento.domain.remote.seasons.Season

interface SeasonsDataSource {
    suspend fun get() : List<Season>?
}