package com.creative.apps.kento.interactors

import com.creative.apps.kento.data.repositories.PopularRepository

class GetPopularAnimesUseCase(private val repository : PopularRepository) {
    suspend operator fun invoke(page : Int) = repository.getPupularAnimes(page)
}