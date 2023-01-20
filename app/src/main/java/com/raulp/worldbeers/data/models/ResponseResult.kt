package com.raulp.worldbeers.data.models

sealed class ResponseResult<T> {
    class Success<T>(val value: T) : ResponseResult<T>()
    class Failure<T>(val message: String) : ResponseResult<T>()
}
