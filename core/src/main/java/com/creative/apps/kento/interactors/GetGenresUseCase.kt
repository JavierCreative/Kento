package com.creative.apps.kento.interactors

import com.creative.apps.kento.data.repositories.GenresRepository

class GetGenresUseCase(private val repository: GenresRepository) {
    suspend operator fun invoke() = repository.getGenres()
}