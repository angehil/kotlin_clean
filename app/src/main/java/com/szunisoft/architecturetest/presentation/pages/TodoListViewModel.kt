package com.szunisoft.architecturetest.presentation.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.szunisoft.architecturetest.data.utils.ResponseResult
import com.szunisoft.architecturetest.domain.model.Todo
import com.szunisoft.architecturetest.domain.usecase.TodosUseCase
import com.szunisoft.architecturetest.presentation.model.TodoItem
import com.szunisoft.architecturetest.presentation.model.mapToPresentation
import com.szunisoft.architecturetest.presentation.utils.ScopedViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

/*  +---------------------[ VIEWMODEL ]------------------------+
    | Role:                                                    |
    |     Expose data to be displayed by the UI                |
    |     Trigger actions on use cases based on user actions   |
    |     Launch and cancel coroutines                         |
    | Dependencies:                                            |
    |     Use cases                                            |
    | Input:                                                   |
    |     id (optional)                                        |
    |     user actions                                         |
    | Output:                                                  |
    |     LiveData<UIModel>                                    |
    +----------------------------------------------------------+ */
class TodoListViewModel(
        private val todosUseCase: TodosUseCase
) : ScopedViewModel() {

    /* ------------------------ [ PROPERTIES ] ----------------------- */

    private val mutableNormalTodos = MutableLiveData<List<TodoItem>>()
    val normalTodos: LiveData<List<TodoItem>>
        get() = mutableNormalTodos

    /* --------------------------- [ INIT ] -------------------------- */

    init {
        // When a viewmodel is created the user wants to see fresh data
        refresh()
    }

    /* ------------------------ [ FUNCTIONS ] ------------------------ */

    /**
     * Refreshes the all the data that this viewmodel is working with
     */
    private fun refresh() {
        this.launch(context = coroutineContext) {
            getAllTodos(true)
        }
    }

    suspend fun getAllTodos(refresh: Boolean = false) {
        val result: ResponseResult<List<Todo>> = todosUseCase()
        when (result) {
            is ResponseResult.Success ->
                mutableNormalTodos.postValue(result.data.mapToPresentation())
            is ResponseResult.Error -> Timber.e(result.exception)
        }
    }
}