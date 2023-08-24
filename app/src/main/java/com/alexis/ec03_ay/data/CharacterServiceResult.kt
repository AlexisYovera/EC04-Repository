package com.alexis.ec03_ay.data

import java.lang.Exception

sealed class CharacterServiceResult<T>(data:T? = null, error: Exception? = null){
    data class Succes<T>(val data: T):CharacterServiceResult<T>(data,null)
    data class Error<T>(val error: Exception):CharacterServiceResult<T>(null,error)
}
