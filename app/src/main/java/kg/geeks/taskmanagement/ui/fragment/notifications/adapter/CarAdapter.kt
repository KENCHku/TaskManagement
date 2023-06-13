package kg.geeks.taskmanagement.ui.fragment.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import kg.geeks.taskmanagement.data.model.car.Car
import kg.geeks.taskmanagement.data.model.task.Task
import kg.geeks.taskmanagment.databinding.ItemTaskBinding

class CarAdapter() : RecyclerView.Adapter<CarAdapter.TaskViewHolder>() {

    private val arrayList = ArrayList<Car>()

    fun addTasks(cars: List<Car>) {
        arrayList.clear()
        arrayList.addAll(cars)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarAdapter.TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CarAdapter.TaskViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount() = arrayList.size

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: Car) = with(binding) {
            tvTitle.setText(car.name)
            tvDesc.setText(car.model)
        }
    }
}