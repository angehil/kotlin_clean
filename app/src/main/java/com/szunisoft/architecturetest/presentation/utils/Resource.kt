package com.szunisoft.architecturetest.presentation.utils

data class Resource<out T> constructor(
        val state: ResourceState,
        val data: T? = null,
        val message: String? = null
)