package com.creative.apps.kento.framework.viewmodels.contracts

import com.creative.apps.kento.domain.remote.characters.Character
import com.creative.apps.kento.framework.viewmodels.base.InterfaceView

interface AnimeCharactersContract {

    interface AnimeCharactersInterfaceView : InterfaceView {
        fun populateRecyclerView(characters : List<Character>?)
    }

    interface AnimeCharactersViewModelInterface {
        fun getCharacters(id : Int)
    }
}