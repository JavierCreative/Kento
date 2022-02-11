package com.creative.apps.kento.framework.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creative.apps.kento.domain.local.ServiceError
import com.creative.apps.kento.domain.remote.characters.Character
import com.creative.apps.kento.exception.ServiceErrorException
import com.creative.apps.kento.framework.viewmodels.contracts.AnimeCharactersContract
import com.creative.apps.kento.interactors.GetAnimeCharactersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimeCharactersViewModel
@Inject constructor(private val interactor : GetAnimeCharactersUseCase) : ViewModel(), AnimeCharactersContract.AnimeCharactersViewModelInterface {

    val characterList : MutableLiveData<List<Character>> by lazy {
        MutableLiveData<List<Character>>()
    }

    val error : MutableLiveData<ServiceError> by lazy {
        MutableLiveData<ServiceError>()
    }

    override fun getCharacters(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val characters = interactor.invoke(id)?.map { it.character }
                characterList.postValue(characters)
            } catch (exception : ServiceErrorException) {
                error.postValue(ServiceError(exception.messageException, exception.errorCode))
            }
        }
    }
}