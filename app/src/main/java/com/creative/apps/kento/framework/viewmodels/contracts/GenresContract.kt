package com.creative.apps.kento.framework.viewmodels.contracts

import com.creative.apps.kento.domain.remote.genres.Genre
import com.creative.apps.kento.framework.viewmodels.base.InterfaceView

interface GenresContract {

    interface GenresInterfaceView : InterfaceView {
        fun setAnimeGenres(genres : List<Genre>?)
    }

    interface GenresViewModelInterface {
        fun getGenres()
    }
}