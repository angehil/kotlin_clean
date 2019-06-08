package com.szunisoft.architecturetest.presentation.model

import com.szunisoft.architecturetest.domain.model.Todo

data class TodoItem(val title: String, val isCompleted: Boolean)

// ----------------- Mapper extension functions -----------------
fun Todo.mapToPresentation() = TodoItem(title, isCompleted)

fun List<Todo>.mapToPresentation(): List<TodoItem> = map { it.mapToPresentation() }