package com.szunisoft.architecturetest.data.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ResponseResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : ResponseResult<T>()
    data class Error(val exception: Exception) : ResponseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
