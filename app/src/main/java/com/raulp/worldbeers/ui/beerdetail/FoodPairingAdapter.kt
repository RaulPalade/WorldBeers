package com.raulp.worldbeers.ui.beerdetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raulp.worldbeers.databinding.FoodPairingListItemBinding

/**
 * @author Raul Palade
 * @date 21/01/2023
 * @project WorldBeers
 */

class FoodPairingAdapter :
    ListAdapter<String, FoodPairingAdapter.FoodPairingViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    class FoodPairingViewHolder(private var binding: FoodPairingListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(food: String) {
            binding.food.text = "• $food"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodPairingViewHolder {
        return FoodPairingViewHolder(
            FoodPairingListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FoodPairingViewHolder, position: Int) {
        val currentFood = getItem(position)
        holder.bind(currentFood)
    }
}