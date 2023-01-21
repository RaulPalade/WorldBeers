package com.raulp.worldbeers.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.raulp.worldbeers.data.models.Beer
import com.raulp.worldbeers.databinding.BeerListItemBinding

/**
 * @author Raul Palade
 * @date 20/01/2023
 * @project WorldBeers
 */

class BeerListAdapter(private val onBeerClicked: (Beer) -> Unit) :
    ListAdapter<Beer, BeerListAdapter.BeerViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Beer>() {
            override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    class BeerViewHolder(private var binding: BeerListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(beer: Beer) {
            binding.apply {
                binding.root.context.let {
                    Glide.with(it)
                        .load(beer.imageUrl)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(binding.beerImage)
                }
                beerName.text = beer.name
                beerDescription.text = beer.description
                beerAbv.text = "ABG: ${beer.abv}%"
                beerIbu.text = "IBU: ${beer.ibu}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        return BeerViewHolder(
            BeerListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val currentBeer = getItem(position)
        holder.itemView.setOnClickListener { onBeerClicked(currentBeer) }
        holder.bind(currentBeer)
    }
}