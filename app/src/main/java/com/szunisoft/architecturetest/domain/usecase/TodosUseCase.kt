package com.szunisoft.architecturetest.domain.usecase

import com.szunisoft.architecturetest.data.model.mapToDomain
import com.szunisoft.architecturetest.data.repository.TodoRepository
import com.szunisoft.architecturetest.data.utils.ResponseResult
import com.szunisoft.architecturetest.domain.model.Todo

/*  +------------------[ USE CASE ]--------------------+
    | Role:                                            |
    |     Processing data based on business logic      |
    |     It has a single task responsibility          |
    | Dependencies:                                    |
    |     Repositories                                 |
    |     and/or UseCases                              |
    | Input:                                           |
    |     ids                                          |
    | Output:                                          |
    |     ResponseResult<T>                            |
    +--------------------------------------------------+ */
class TodosUseCase(
        private val todoRepository: TodoRepository
) {

    suspend operator fun invoke(): ResponseResult<List<Todo>> {

        val result = todoRepository.getAll()

        return when (result) {
            is ResponseResult.Success -> ResponseResult.Success(result.data.mapToDomain())
            is ResponseResult.Error -> ResponseResult.Error(result.exception)
        }
    }
}
