package com.bgaprojects.barus.collections

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bgaprojects.barus.collections.model.HabitItem
import com.bgaprojects.barus.databinding.HabitItemBinding

class HabitListAdapter(
    private val viewModel: HabitListViewModel
) : RecyclerView.Adapter<HabitListAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<HabitItem> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ViewHolder(
        private val binding: HabitItemBinding,
        private val viewModel: HabitListViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

    }
}

object DiffCallback : DiffUtil.ItemCallback<HabitItem>() {
    override fun areItemsTheSame(oldItem: HabitItem, newItem: HabitItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HabitItem, newItem: HabitItem): Boolean {
        return oldItem.isCompleted == newItem.isCompleted
    }

}