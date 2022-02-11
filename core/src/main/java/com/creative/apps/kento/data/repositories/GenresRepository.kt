package com.creative.apps.kento.data.repositories

import com.creative.apps.kento.data.datasources.GenresDataSource

class GenresRepository(private val dataSource: GenresDataSource) {
    suspend fun getGenres() = dataSource.get()
}