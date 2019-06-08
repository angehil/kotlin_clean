package com.szunisoft.architecturetest.presentation.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.szunisoft.architecturetest.R
import androidx.recyclerview.widget.RecyclerView
import com.szunisoft.architecturetest.presentation.model.TodoItem
import kotlinx.android.synthetic.main.item_list_todo.view.*
import java.util.ArrayList

class TodoListAdapter constructor(private val itemClick: (TodoItem) -> Unit) :
        RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    private val items = ArrayList<TodoItem>()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(parent: ViewGroup) :
            RecyclerView.ViewHolder(parent.inflate(R.layout.item_list_todo)) {

        fun bind(item: TodoItem) {
            itemView.title_text.text = item.title
            itemView.completed_chip.isCheckedIconVisible = true
            itemView.completed_chip.isCheckable = true
            itemView.completed_chip.isChecked = item.isCompleted
            if(item.isCompleted)
                itemView.completed_chip.setChipBackgroundColorResource(R.color.material_deep_teal_200)
            else
                itemView.completed_chip.setChipBackgroundColorResource(R.color.material_grey_300)
            itemView.completed_chip.text = if (item.isCompleted) "Done" else "Open"
            itemView.setOnClickListener { itemClick.invoke(item) }
        }
    }

    fun addItems(list: List<TodoItem>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
