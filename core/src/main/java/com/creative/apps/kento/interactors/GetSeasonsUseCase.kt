package com.creative.apps.kento.interactors

import com.creative.apps.kento.data.repositories.SeasonsRepository


class GetSeasonsUseCase(private val repository: SeasonsRepository) {
    suspend operator fun invoke() = repository.getSeasons()
}