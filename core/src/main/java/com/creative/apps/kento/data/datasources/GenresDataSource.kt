package com.creative.apps.kento.data.datasources

import com.creative.apps.kento.domain.remote.genres.Genre

interface GenresDataSource {
   suspend fun get() : List<Genre>?
}