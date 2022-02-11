package com.creative.apps.kento.exception

class ServiceErrorException(val messageException : String, val errorCode : Int) : Exception(messageException)