package com.creative.apps.kento.framework.viewmodels.base

import com.creative.apps.kento.domain.local.ServiceError

interface InterfaceView {
    fun showProgress()
    fun hideProgress()
    fun onError(error : ServiceError)
}