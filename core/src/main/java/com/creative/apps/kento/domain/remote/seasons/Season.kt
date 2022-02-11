package com.creative.apps.kento.domain.remote.seasons

import kotlinx.serialization.Serializable

@Serializable
data class Season(val year : Int, val seasons : List<String>)
