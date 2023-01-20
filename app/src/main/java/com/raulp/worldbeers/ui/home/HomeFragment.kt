package com.raulp.worldbeers.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.raulp.worldbeers.data.datasource.WorldBeerDataSource
import com.raulp.worldbeers.data.models.ResponseStatus
import com.raulp.worldbeers.data.network.RetrofitProvider
import com.raulp.worldbeers.data.repository.BeerRepository
import com.raulp.worldbeers.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val retrofitService =
        RetrofitProvider.createWorldBeerService(WorldBeerDataSource::class.java)
    private val beerRepository = BeerRepository(retrofitService)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        binding.lifecycleOwner = this
        binding.homeViewModel = viewModel

        val adapter = BeerListAdapter {
            val action = HomeFragmentDirections.actionBeerListFragmentToBeerDetail(it.id)
            this.findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter

        viewModel.getBeerList()

        viewModel.beers.observe(viewLifecycleOwner) { beers ->
            when (beers) {
                is ResponseStatus.Success -> {
                    adapter.submitList(beers.value)
                }
                is ResponseStatus.Failure -> {
                    println("NO BEERS FOUND")
                }
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(
                this,
                HomeViewModelFactory(beerRepository)
            )[HomeViewModel::class.java]
    }
}