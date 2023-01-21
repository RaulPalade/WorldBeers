package com.raulp.worldbeers.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.raulp.worldbeers.R
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
            val action = HomeFragmentDirections.actionBeerListFragmentToBeerDetail(it)
            this.findNavController().navigate(action)
        }
        binding.homeRecyclerView.adapter = adapter
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(this.context)

        viewModel.getBeerList()

        viewModel.beers.observe(viewLifecycleOwner) { beers ->
            binding.refreshView.isRefreshing = false
            when (beers) {
                is ResponseStatus.Success -> {
                    adapter.submitList(beers.value)
                }
                is ResponseStatus.Failure -> {
                    errorSnackbar(beers.message)
                }
            }
        }

        binding.filter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { text ->
                    viewModel.filterBeers(text)
                }
                return true
            }
        })

        binding.refreshView.setOnRefreshListener {
            viewModel.getBeerList()
        }
        binding.refreshView.isRefreshing = true
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(
                this,
                HomeViewModelFactory(beerRepository)
            )[HomeViewModel::class.java]
    }

    @Suppress("SameParameterValue")
    private fun successSnackbar(message: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).setBackgroundTint(
            ContextCompat.getColor(
                this.requireContext(),
                R.color.green
            )
        ).setTextColor(
            ContextCompat.getColor(
                this.requireContext(),
                R.color.black
            )
        ).show()
    }

    @Suppress("SameParameterValue")
    private fun errorSnackbar(message: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_SHORT
        ).setBackgroundTint(
            ContextCompat.getColor(
                this.requireContext(),
                R.color.orange
            )
        ).setTextColor(
            ContextCompat.getColor(
                this.requireContext(),
                R.color.white
            )
        ).show()
    }
}