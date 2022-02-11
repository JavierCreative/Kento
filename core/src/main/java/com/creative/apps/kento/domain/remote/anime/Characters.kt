package com.creative.apps.kento.domain.remote.anime

import com.creative.apps.kento.domain.remote.characters.Character
import kotlinx.serialization.Serializable

@Serializable
data class Characters(val character : Character)