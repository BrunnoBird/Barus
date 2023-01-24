package com.bgaprojects.barus.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bgaprojects.barus.collections.domain.GetHabitsForTodayUseCaseImpl
import com.bgaprojects.barus.collections.domain.ToggleProgressUseCaseImpl
import com.bgaprojects.barus.core.repository.HabitRepositoryImpl
import com.bgaprojects.barus.core.repository.ProgressRepository
import com.bgaprojects.barus.core.repository.ProgressRepositoryImpl
import com.bgaprojects.barus.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterHabitList: HabitListAdapter

    private val viewModel: HabitListViewModel by activityViewModels {
        val habitRepository = HabitRepositoryImpl
        val progressRepository = ProgressRepositoryImpl
        val getHabitsForTodayUseCase = GetHabitsForTodayUseCaseImpl(
            progressRepository = progressRepository,
            habitRepository = habitRepository,
        )

        HabitListViewModel.Factory(
            getHabitsForTodayUseCase = getHabitsForTodayUseCase,
            toggleProgressUseCase = ToggleProgressUseCaseImpl(progressRepository)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(HabitListLifecycleObserver(viewModel))
        adapterHabitList = HabitListAdapter(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.stateOnceAndStream().observe(viewLifecycleOwner) {
            bindUiState(it)
        }
    }

    private fun bindUiState(uiState: HabitListViewModel.UiState) {
        adapterHabitList.updateHabits(uiState.habitItemList)
    }

    private fun setupRecyclerView() {
        binding.rvDayHabit.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDayHabit.adapter = adapterHabitList
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}