package com.szunisoft.architecturetest.data.sources

import com.szunisoft.architecturetest.data.model.TodoEntity
import com.szunisoft.architecturetest.data.remote.TodosService
import com.szunisoft.architecturetest.data.utils.ResponseResult
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException

/*  +-------------[ REMOTE DATA SOURCE ]---------------+
    | Role:                                            |
    |     Constructing the request data                |
    |     Fetching data from a service                 |
    | Dependencies:                                    |
    |     API Service                                  |
    | Input:                                           |
    |     Request information                          |
    | Output:                                          |
    |     ResponseResult<TResponse>                    |
    +--------------------------------------------------+ */
class TodosRemoteDataSource(
        private val service: TodosService
) {

    suspend fun getAllTodos(): ResponseResult<List<TodoEntity>> = coroutineScope {
        withContext(IO) {
            Timber.d(Thread.currentThread().name)
            val response = service.getTodos().await()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return@withContext ResponseResult.Success(body)
                }
            }
            ResponseResult.Error(
                    IOException("Error getting todos ${response.code()} ${response.message()}")
            )
        }


    }
}