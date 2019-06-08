package com.szunisoft.architecturetest

import com.szunisoft.architecturetest.data.remote.BASE_URL
import com.szunisoft.architecturetest.data.remote.TodosService
import com.szunisoft.architecturetest.data.remote.createNetworkClient
import com.szunisoft.architecturetest.data.repository.TodoRepository
import com.szunisoft.architecturetest.data.sources.TodosRemoteDataSource
import com.szunisoft.architecturetest.domain.usecase.TodosUseCase
import com.szunisoft.architecturetest.presentation.pages.TodoListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel { TodoListViewModel(todosUseCase = get()) }
}

val useCaseModule = module {
    factory { TodosUseCase(todoRepository = get()) }
}

val repositoryModule = module {
    single { TodoRepository(remoteSource =  get()) }
}

val remoteDataSourceModule = module {
    single { TodosRemoteDataSource(service = get()) }
}

val localDataSourceModule = module {

}
val networkModule = module {
    single { todosService }
}


private val retrofit: Retrofit = createNetworkClient(BASE_URL, BuildConfig.DEBUG)

private val todosService: TodosService = retrofit.create(TodosService::class.java)
