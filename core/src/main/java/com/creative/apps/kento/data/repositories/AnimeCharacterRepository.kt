package com.creative.apps.kento.data.repositories

import com.creative.apps.kento.data.datasources.AnimeCharactersDataSource

class AnimeCharacterRepository(private val dataSource : AnimeCharactersDataSource) {
    suspend fun getAnimeCharacters(id : Int) = dataSource.get(id)
}