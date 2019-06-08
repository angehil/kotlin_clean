package com.szunisoft.architecturetest.presentation.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.szunisoft.architecturetest.R
import com.szunisoft.architecturetest.databinding.FragmentTodoitemBinding
import com.szunisoft.architecturetest.presentation.utils.TodoListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.viewModel
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class TodoItemFragment : Fragment() {

    private val vm: TodoListViewModel by viewModel()
    private lateinit var binding: FragmentTodoitemBinding
    private val adapter = TodoListAdapter { todo ->

        binding.root.snackbar("${todo.title} was clicked", "Is it done?") {
            context!!.toast("${todo.isCompleted}")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todoitem, container, false)
        // Set the view model of the view
        binding.vm = vm
        // Specify the current fragment as the lifecycle owner.
        binding.setLifecycleOwner(this)
        // Set adapters
        binding.todoRecycleView.adapter = adapter

        vm.normalTodos.observe(this, Observer {
            adapter.addItems(it)
        })

        return binding.root
    }
}
