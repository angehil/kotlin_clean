package com.szunisoft.architecturetest.presentation.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 * Contains the boilerplate code for a viewmodel
 * to be able to launch a coroutine with a job that cancels automatically on destroy
 * and witch is scoped, so all child coroutines are cancelled in this case
 */
open class ScopedViewModel : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        // Disposes all subscriptions to prevent memory leak
        super.onCleared()
        Timber.d("Job cancelled")
        job.cancel()
    }
}