package com.creative.apps.kento.interactors

import com.creative.apps.kento.data.repositories.AnimeInformationRepository

class GetAnimeInformationUseCase(private val repository : AnimeInformationRepository) {
    suspend operator fun invoke(id : Int) = repository.getAnimeInformation(id)
}