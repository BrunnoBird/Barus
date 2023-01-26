package com.bgaprojects.barus.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bgaprojects.barus.core.repository.HabitRepositoryImpl
import com.bgaprojects.barus.databinding.FragmentHabitFormBinding
import com.google.android.material.chip.Chip

class HabitFormFragment : Fragment() {

    private var _binding: FragmentHabitFormBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HabitFormViewModel by viewModels {
        HabitFormViewModel.Factory(HabitRepositoryImpl)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHabitFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSaveHabit.setOnClickListener {
            onSave()
        }
    }

    private fun onSave() {
        val habitName = binding.tilHabitFormName.editText?.text.toString()

        val habitDaysSelected = mutableListOf<Int>()

        for (id in binding.cgDaysOfWeek.checkedChipIds) {
            val chip = binding.cgDaysOfWeek.findViewById<Chip>(id)
            val position = binding.cgDaysOfWeek.indexOfChild(chip)
            habitDaysSelected.add(position + 1)
        }

        viewModel.addHabit(habitName, habitDaysSelected)

        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}