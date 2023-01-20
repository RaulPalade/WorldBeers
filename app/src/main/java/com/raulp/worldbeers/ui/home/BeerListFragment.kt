package com.raulp.worldbeers.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.raulp.worldbeers.databinding.FragmentBeerListBinding

class BeerListFragment : Fragment() {
    private lateinit var binding: FragmentBeerListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBeerListBinding.inflate(inflater, container, false)
        return binding.root
    }
}