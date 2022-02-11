package com.creative.apps.kento.framework.viewmodels.contracts

import com.creative.apps.kento.domain.remote.characters.CharacterInformation
import com.creative.apps.kento.framework.viewmodels.base.InterfaceView

interface CharacterInformationContract {

    interface CharacterInformationInterfaceView : InterfaceView {
        fun setCharacterInformation(character : CharacterInformation?)
    }

    interface CharacterInformationViewModelInterface {
        fun getCharacterInformation(id : Int)
    }
}