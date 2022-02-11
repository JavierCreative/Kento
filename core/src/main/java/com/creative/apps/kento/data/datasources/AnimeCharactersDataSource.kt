package com.creative.apps.kento.data.datasources

import com.creative.apps.kento.domain.remote.anime.Characters
interface AnimeCharactersDataSource {
    suspend fun get(id : Int) : List<Characters>?
}