package kg.geeks.taskmanagement.ui.fragment.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kg.geeks.taskmanagement.data.model.onboarding.OnBoard
import kg.geeks.taskmanagement.utils.ext.loadImage
import kg.geeks.taskmanagment.databinding.ItemOnBoardBinding

class OnBoardAdapter(private val startClick: () -> Unit) :
    Adapter<OnBoardAdapter.OnBoardViewHolder>() {

    private val boards = arrayListOf<OnBoard>(
        OnBoard("https://png.pngtree.com/png-clipart/20190925/original/pngtree-work-plan-list-png-image_4896609.jpg", "Aim", "Get done your plans"),
        OnBoard("https://img.freepik.com/free-vector/project-management-goal-completion-list-questionnaire-survey-answering-business-organization-tool_335657-3289.jpg?w=2000", "Note", "Simple reminder"),
        OnBoard("https://img.freepik.com/free-vector/business-background-design_1300-348.jpg?w=2000", "Plan", "Organize your targets")
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardAdapter.OnBoardViewHolder {
        return OnBoardViewHolder(ItemOnBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OnBoardAdapter.OnBoardViewHolder, position: Int) {
        holder.bind(boards[position])
    }

    override fun getItemCount(): Int {
        return boards.size
    }

    inner class OnBoardViewHolder(private val binding: ItemOnBoardBinding) :
        ViewHolder(binding.root) {

        fun bind(onBoard: OnBoard) {
            binding.ivOnBoard.loadImage(onBoard.image)
            binding.tvTitle.setText(onBoard.title)
            binding.tvDesc.setText(onBoard.desc)

            binding.btnStart.isVisible = adapterPosition == boards.lastIndex
            binding.btnStart.setOnClickListener {
                startClick()
            }
        }
    }
}