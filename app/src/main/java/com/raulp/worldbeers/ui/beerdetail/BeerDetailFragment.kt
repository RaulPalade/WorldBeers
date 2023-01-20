package com.raulp.worldbeers.ui.beerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
}