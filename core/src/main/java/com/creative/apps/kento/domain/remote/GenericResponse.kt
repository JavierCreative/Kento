package com.creative.apps.kento.domain.remote

import kotlinx.serialization.Serializable

@Serializable
data class GenericResponse<T>(val data: T)