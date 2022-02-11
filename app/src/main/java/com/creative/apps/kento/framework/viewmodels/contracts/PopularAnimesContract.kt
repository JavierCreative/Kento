package com.creative.apps.kento.framework.viewmodels.contracts

import com.creative.apps.kento.domain.remote.anime.Anime
import com.creative.apps.kento.framework.viewmodels.base.InterfaceView

interface PopularAnimesContract {

    interface PopularAnimesInterfaceView : InterfaceView {
        fun setAnimes(animes : List<Anime>?)
    }

    interface PopularAnimesViewModelInterface {
        fun getAnimes(page : Int)
    }
}