package com.creative.apps.kento.framework.viewmodels.contracts

import com.creative.apps.kento.domain.remote.anime.Anime
import com.creative.apps.kento.framework.viewmodels.base.InterfaceView

interface AnimeInformationContract {

    interface AnimeInformationInterfaceView : InterfaceView {
        fun setInformation(anime : Anime?)
    }

    interface AnimeInformationViewModelInterface {
        fun getAnimeInformation(id : Int)
    }
}