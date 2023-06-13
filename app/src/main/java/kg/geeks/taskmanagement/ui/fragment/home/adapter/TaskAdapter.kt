package kg.geeks.taskmanagement.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import kg.geeks.taskmanagement.data.model.task.Task
import kg.geeks.taskmanagment.databinding.ItemTaskBinding

class TaskAdapter(
    private val itemClick: (Task) -> Unit,
    private val itemLongClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val arrayList = ArrayList<Task>()

    fun addTasks(tasks: List<Task>) {
        arrayList.clear()
        arrayList.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount() = arrayList.size

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) = with(binding) {
            tvTitle.setText(task.title)
            tvDesc.setText(task.desc)

            binding.root.setOnClickListener {
                itemClick(task)
            }

            itemView.setOnLongClickListener {
                itemLongClick(task)
                false
            }
        }
    }
}