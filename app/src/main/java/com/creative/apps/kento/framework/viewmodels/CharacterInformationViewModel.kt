package com.creative.apps.kento.framework.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creative.apps.kento.domain.local.ServiceError
import com.creative.apps.kento.domain.remote.characters.CharacterInformation
import com.creative.apps.kento.exception.ServiceErrorException
import com.creative.apps.kento.framework.viewmodels.contracts.CharacterInformationContract
import com.creative.apps.kento.interactors.GetCharacterInformationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterInformationViewModel
@Inject constructor(private val interactor : GetCharacterInformationUseCase) : ViewModel(), CharacterInformationContract.CharacterInformationViewModelInterface {

    val characterInformation : MutableLiveData<CharacterInformation> by lazy {
        MutableLiveData<CharacterInformation>()
    }

    val error : MutableLiveData<ServiceError> by lazy {
        MutableLiveData<ServiceError>()
    }

    override fun getCharacterInformation(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val information = interactor.invoke(id)
                characterInformation.postValue(information)
            } catch (exception : ServiceErrorException) {
                error.postValue(ServiceError(exception.messageException, exception.errorCode))
            }
        }
    }
}