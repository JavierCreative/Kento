package com.creative.apps.kento.framework.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creative.apps.kento.domain.remote.genres.Genre
import com.creative.apps.kento.domain.local.ServiceError
import com.creative.apps.kento.exception.ServiceErrorException
import com.creative.apps.kento.framework.viewmodels.contracts.GenresContract
import com.creative.apps.kento.interactors.GetGenresUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GenresViewModel
@Inject constructor(private val interactor : GetGenresUseCase) : ViewModel(), GenresContract.GenresViewModelInterface {

    val genresList : MutableLiveData<List<Genre>> by lazy {
        MutableLiveData<List<Genre>>()
    }

    val error : MutableLiveData<ServiceError> by lazy {
        MutableLiveData<ServiceError>()
    }

    override fun getGenres() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val genres = interactor.invoke()?.distinctBy { it.id }
                genresList.postValue(genres)
            } catch (exception : ServiceErrorException) {
                error.postValue(ServiceError(exception.messageException, exception.errorCode))
            }
        }
    }
}