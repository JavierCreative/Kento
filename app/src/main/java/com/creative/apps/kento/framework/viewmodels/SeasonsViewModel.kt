package com.creative.apps.kento.framework.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creative.apps.kento.domain.local.ServiceError
import com.creative.apps.kento.exception.ServiceErrorException
import com.creative.apps.kento.framework.viewmodels.contracts.SeasonsContract
import com.creative.apps.kento.interactors.GetSeasonsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SeasonsViewModel
@Inject constructor(private val interactor : GetSeasonsUseCase) : ViewModel(), SeasonsContract.SeasonsViewModelInterface {

    val seasonsList : MutableLiveData<List<Int>> by lazy {
        MutableLiveData<List<Int>>()
    }

    val error : MutableLiveData<ServiceError> by lazy {
        MutableLiveData<ServiceError>()
    }


    override fun getSeasons() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val years = interactor.invoke()?.filter { it.year >= 1965 }?.map {it.year}
                seasonsList.postValue(years)
            } catch (exception : ServiceErrorException) {
                error.postValue(ServiceError(exception.messageException, exception.errorCode))
            }
        }
    }

}