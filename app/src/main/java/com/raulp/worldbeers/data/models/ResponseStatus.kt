package com.raulp.worldbeers.data.models

sealed class ResponseStatus<T> {
    class Success<T>(val value: T) : ResponseStatus<T>()
    class Failure<T>(val message: String) : ResponseStatus<T>()
}
