package com.creative.apps.kento.framework.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creative.apps.kento.domain.remote.anime.Anime
import com.creative.apps.kento.domain.local.ServiceError
import com.creative.apps.kento.exception.ServiceErrorException
import com.creative.apps.kento.framework.viewmodels.contracts.AnimeInformationContract
import com.creative.apps.kento.interactors.GetAnimeInformationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimeInformationViewModel
@Inject constructor(private val interactor : GetAnimeInformationUseCase) : ViewModel(), AnimeInformationContract.AnimeInformationViewModelInterface{

    val animeInformation : MutableLiveData<Anime> by lazy {
        MutableLiveData<Anime>()
    }

    val error : MutableLiveData<ServiceError> by lazy {
        MutableLiveData<ServiceError>()
    }


    override fun getAnimeInformation(id: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val anime = interactor.invoke(id)
                animeInformation.postValue(anime)
            } catch (exception : ServiceErrorException) {
                error.postValue(ServiceError(exception.messageException, exception.errorCode))
            }
        }

    }

}