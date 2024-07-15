package com.marvel.core.utils

sealed class BaseResponse<T>(val data: T? = null, val message: String?= null) {
    class Loading<T>(data: T?= null):BaseResponse<T>(data)
    class Success<T>(data: T): BaseResponse<T>(data)
    class Error<T>(message: String?, data: T?=null):BaseResponse<T>(data,message)
}