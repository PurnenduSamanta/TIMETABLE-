package com.purnendu.timetable.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.purnendu.timetable.R
import com.purnendu.timetable.databinding.SingleTaskLayoutBinding
import com.purnendu.timetable.model.Task


class TaskRecyclerViewAdapter(
    private val context: Context,
    private val onDeleteTask: (taskId: Long, position: Int) -> Unit
) : RecyclerView.Adapter<TaskRecyclerViewAdapter.MyViewHolder>() {


    private var taskList = emptyList<Task>()

    fun setTaskList(taskList: List<Task>) {
        this.taskList = taskList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SingleTaskLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.taskNameText.text = taskList[position].taskName
        holder.binding.taskDescText.text = taskList[position].taskDesc

        holder.binding.optionMenuIcon.setOnClickListener {
            createDeleteMenu(holder,position)
        }

    }

    override fun getItemCount(): Int {
        return taskList.size
    }


   // fun updateRecyclerViewAfterDeletion(position: Int) = notifyItemRemoved(position)

    private fun createDeleteMenu(holder:MyViewHolder,position: Int) {
        val popupMenu = PopupMenu(context, holder.binding.optionMenuIcon)
        popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
        popupMenu.setForceShowIcon(true)
        popupMenu.setOnMenuItemClickListener {
            onDeleteTask(taskList[position].taskId, position)
            true
        }
        popupMenu.show()
    }


    class MyViewHolder(val binding: SingleTaskLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}