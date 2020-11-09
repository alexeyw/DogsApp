package com.avolodin.dogsapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avolodin.dogsapp.R
import com.avolodin.dogsapp.databinding.ViewImageItemBinding
import com.avolodin.dogsapp.ui.model.ImageView
import com.squareup.picasso.Picasso
import javax.inject.Inject


class BreedImageListAdapter @Inject constructor(private val picasso: Picasso) :
    ListAdapter<ImageView, BreedImageListAdapter.ViewHolder>(BreedImageDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, picasso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ViewImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ImageView, picasso: Picasso?) {
            picasso?.load(item.url)?.into(binding.photo, object : com.squareup.picasso.Callback {
                override fun onError(e: Exception?) {
                    binding.photo.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.photo.context,
                            R.drawable.ic_baseline_cancel_presentation_24
                        )
                    )
                }

                override fun onSuccess() {
                    binding.apply {
                        photo.visibility = View.VISIBLE
                        imageLoadingProgress.visibility = View.GONE
                    }
                }
            })
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewImageItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class BreedImageDiffCallback : DiffUtil.ItemCallback<ImageView>() {
    override fun areItemsTheSame(oldItem: ImageView, newItem: ImageView): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: ImageView, newItem: ImageView): Boolean {
        return oldItem == newItem
    }
}