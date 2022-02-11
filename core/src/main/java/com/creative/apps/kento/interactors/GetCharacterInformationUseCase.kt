package com.creative.apps.kento.interactors

import com.creative.apps.kento.data.repositories.CharacterInformationRepository

class GetCharacterInformationUseCase(private val repository : CharacterInformationRepository) {
    suspend operator fun invoke(id : Int) = repository.getCharacterInformation(id)
}