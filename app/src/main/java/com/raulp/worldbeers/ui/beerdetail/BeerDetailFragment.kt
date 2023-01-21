package com.raulp.worldbeers.ui.beerdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.raulp.worldbeers.data.models.Beer
import com.raulp.worldbeers.databinding.FragmentBeerDetailBinding

class BeerDetailFragment : Fragment() {
    private lateinit var binding: FragmentBeerDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBeerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: BeerDetailFragmentArgs by navArgs()
        val beer = args.beer
        setInfo(beer)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = beer.name

        val adapter = FoodPairingAdapter()
        binding.foodPairingRecyclerView.adapter = adapter
        binding.foodPairingRecyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter.submitList(beer.foodPairing)
    }

    @SuppressLint("SetTextI18n")
    private fun setInfo(beer: Beer) {
        binding.apply {
            context?.let {
                Glide.with(it)
                    .load(beer.imageUrl)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.beerImage)
            }
            beerDescription.text = beer.description
            firstBrewed.text = "First Brewed: ${beer.firstBrewed}"
            brewerTips.text = beer.brewerTips
        }
    }
}