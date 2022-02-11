package com.creative.apps.kento.framework.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creative.apps.kento.domain.remote.anime.Anime
import com.creative.apps.kento.domain.local.ServiceError
import com.creative.apps.kento.exception.ServiceErrorException
import com.creative.apps.kento.framework.viewmodels.contracts.PopularAnimesContract
import com.creative.apps.kento.interactors.GetPopularAnimesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularAnimesViewModel
@Inject constructor(private val interactor : GetPopularAnimesUseCase) :
    ViewModel(), PopularAnimesContract.PopularAnimesViewModelInterface {

    val popularAnimeList : MutableLiveData<List<Anime>> by lazy {
        MutableLiveData<List<Anime>>()
    }

    val error : MutableLiveData<ServiceError> by lazy {
        MutableLiveData<ServiceError>()
    }

    override fun getAnimes(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val animeList = interactor.invoke(page)
                popularAnimeList.postValue(animeList)
            } catch (exception : ServiceErrorException) {
                error.postValue(ServiceError(exception.messageException, exception.errorCode))
            }
        }
    }

}