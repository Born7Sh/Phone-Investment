package com.example.stock.views.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.stock.R
import com.example.stock.adapter.StockAdapter
import com.example.stock.data.model.Stock
import com.example.stock.data.repository.StockRepository
import com.example.stock.databinding.FragmentFavoriteBinding
import com.example.stock.viewmodel.FavoriteViewModel
import com.example.stock.viewmodel.MainViewModel
import com.example.stock.viewmodel.RepositoryViewModelFactory


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var repositoryViewModelFactory: RepositoryViewModelFactory

    private lateinit var stockAdapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // viewModel 설정
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        repositoryViewModelFactory = RepositoryViewModelFactory(StockRepository())
        favoriteViewModel =
            ViewModelProvider(this, repositoryViewModelFactory).get(FavoriteViewModel::class.java)


        stockAdapter = StockAdapter()
        binding.recycler.adapter = stockAdapter
        binding.favoriteFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteViewModel.updateFavoriteStockList()

        favoriteViewModel.favoriteStockList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            stockAdapter.setData(it as ArrayList<Stock>)
        })


    }

    fun search(view: View) {
        view.findNavController().navigate(R.id.action_FavoriteFragment_to_searchFragment)
    }

}