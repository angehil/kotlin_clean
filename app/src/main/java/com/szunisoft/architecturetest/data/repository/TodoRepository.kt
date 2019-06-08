package com.szunisoft.architecturetest.data.repository

import com.szunisoft.architecturetest.data.model.TodoEntity
import com.szunisoft.architecturetest.data.sources.TodosRemoteDataSource
import com.szunisoft.architecturetest.data.utils.ResponseResult

/*  +-----------------[ REPOSITORY ]-------------------+
    | Role:                                            |
    |     Fetching and storing data                    |
    |     In-memory cache (optional)                   |
    | Dependencies:                                    |
    |     RemoteDataSource                             |
    |     and/or LocalDataSource                       |
    | Input:                                           |
    |     Data retrieval: ids                          |
    |     Data saving: T                               |
    | Output:                                          |
    |     ResponseResult<TResponse>                    |
    +--------------------------------------------------+ */
class TodoRepository(
        private val remoteSource: TodosRemoteDataSource
) {

   suspend fun getAll(): ResponseResult<List<TodoEntity>> {
       return remoteSource.getAllTodos()
   }
}
