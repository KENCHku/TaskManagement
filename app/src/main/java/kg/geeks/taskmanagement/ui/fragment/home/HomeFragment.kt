package kg.geeks.taskmanagement.ui.fragment.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import kg.geeks.taskmanagement.App
import kg.geeks.taskmanagement.data.model.task.Task
import kg.geeks.taskmanagement.ui.fragment.home.adapter.TaskAdapter
import kg.geeks.taskmanagment.R
import kg.geeks.taskmanagment.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::itemClick, this::itemLongClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewHome.adapter = adapter

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

        getData()
    }

    private fun getData() {
        val tasks = App.db.dao().getAll()
        adapter.addTasks(tasks)
    }

    private fun itemClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf(EDIT_KEY_TASK to task))
    }

    private fun itemLongClick(task: Task) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Item")
            .setMessage("Are you sure you want to delete this item?")
            .setPositiveButton("Delete") { _, _ ->
                App.db.dao().delete(task)
                getData()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    companion object {
        const val EDIT_KEY_TASK = "edit_key.task"
    }
}