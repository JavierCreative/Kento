package com.creative.apps.kento.framework.viewmodels.contracts

import com.creative.apps.kento.framework.viewmodels.base.InterfaceView

interface SeasonsContract {

    interface SeasonsInterfaceView : InterfaceView {
        fun setSeasons(seasons : List<Int>?)
    }

    interface SeasonsViewModelInterface {
        fun getSeasons()
    }

}