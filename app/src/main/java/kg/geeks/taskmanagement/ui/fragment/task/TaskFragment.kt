package kg.geeks.taskmanagement.ui.fragment.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kg.geeks.taskmanagement.App
import kg.geeks.taskmanagement.data.model.task.Task
import kg.geeks.taskmanagement.ui.fragment.home.HomeFragment
import kg.geeks.taskmanagement.utils.ext.showToast
import kg.geeks.taskmanagment.R
import kg.geeks.taskmanagment.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            validate()
        }

        initEditTask()
    }

    private fun validate() {
        if (binding.etTitle.text.toString().isBlank() || binding.etDesc.text.toString().isBlank()) {
            showToast(getString(R.string.empty_fields))
        } else {
            if (binding.btnSave.text.equals(getString(R.string.update))) {
                onUpdate()
            } else {
                onSave()
            }
            findNavController().navigateUp()
        }
    }

    private fun onSave() {
        val data = Task(
            title = binding.etTitle.text.toString().trim(),
            desc = binding.etDesc.text.toString().trim()
        )
        App.db.dao().insert(data)
    }

    private fun onUpdate() {
        val data = task?.copy(
            title = binding.etTitle.text.toString().trim(),
            desc = binding.etDesc.text.toString().trim()
        )

        if (data != null) {
            App.db.dao().update(data)
        }
    }

    private fun initEditTask() {
        task = arguments?.getSerializable(HomeFragment.EDIT_KEY_TASK) as Task?
        task?.let {
            binding.btnSave.setText(getString(R.string.update))
            binding.etTitle.setText(it.title)
            binding.etDesc.setText(it.desc)
        }
    }
}