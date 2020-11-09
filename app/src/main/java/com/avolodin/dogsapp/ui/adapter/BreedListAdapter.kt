package com.avolodin.dogsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avolodin.dogsapp.databinding.ViewBreedItemBinding
import com.avolodin.dogsapp.ui.model.BreedView

class BreedListAdapter(private val itemClickListener: OnBreedItemClickListener) :
    ListAdapter<BreedView, BreedListAdapter.ViewHolder>(BreedDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ViewBreedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BreedView, clickListener: OnBreedItemClickListener) {
            binding.apply {
                title.text = item.name
                breedItem.setOnClickListener { clickListener.onItemClicked(item.name) }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewBreedItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

interface OnBreedItemClickListener {
    fun onItemClicked(breed: String?)
}

class BreedDiffCallback : DiffUtil.ItemCallback<BreedView>() {
    override fun areItemsTheSame(oldItem: BreedView, newItem: BreedView): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: BreedView, newItem: BreedView): Boolean {
        return oldItem == newItem
    }
}