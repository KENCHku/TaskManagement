package kg.geeks.taskmanagement.ui.fragment.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kg.geeks.taskmanagement.data.local.pref.Pref
import kg.geeks.taskmanagement.ui.fragment.onboarding.adapter.OnBoardAdapter
import kg.geeks.taskmanagment.R
import kg.geeks.taskmanagment.databinding.FragmentOnBoardBinding

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private val adapter = OnBoardAdapter(this::startClick)
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = Pref(requireContext())

        binding.viewPager2.adapter = adapter
        binding.circleIndicator3.setViewPager(binding.viewPager2)
    }

    private fun startClick() {
        findNavController().navigateUp()
        pref.boardingIsSeen()
    }
}