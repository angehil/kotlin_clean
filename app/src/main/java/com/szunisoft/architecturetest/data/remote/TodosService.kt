package com.szunisoft.architecturetest.data.remote

import com.szunisoft.architecturetest.data.model.TodoEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TodosService {

    @GET("todos")
    fun getTodos(): Deferred<Response<List<TodoEntity>>>

    @GET("todos/{id}")
    fun getTodo(@Path("id") todoId: String): Deferred<Response<TodoEntity>>
}