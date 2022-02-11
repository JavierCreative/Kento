package com.creative.apps.kento.interactors

import com.creative.apps.kento.data.repositories.AnimeCharacterRepository

class GetAnimeCharactersUseCase(private val repository : AnimeCharacterRepository) {
    suspend operator fun invoke(id : Int) = repository.getAnimeCharacters(id)
}