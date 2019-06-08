package com.szunisoft.architecturetest.data.model

import com.squareup.moshi.Json
import com.szunisoft.architecturetest.domain.model.Todo
import java.util.*

data class TodoEntity(
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "userId") val userId: Int,
        @field:Json(name = "title") val title: String,
        @field:Json(name = "completed") val isCompleted: Boolean)

// ----------------- Mapper extension functions -----------------

fun TodoEntity.mapToDomain(): Todo = Todo(id, userId, title, isCompleted)

fun List<TodoEntity>.mapToDomain(): List<Todo> = map { it.mapToDomain() }
