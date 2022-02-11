package com.creative.apps.kento.data.repositories

import com.creative.apps.kento.data.datasources.PopularAnimesDataSource

class PopularRepository(private val dataSource: PopularAnimesDataSource) {

    suspend fun getPupularAnimes(page : Int) =
        dataSource.get(page)

}