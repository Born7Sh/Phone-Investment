package com.example.stock.views.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.stock.R
import com.example.stock.adapter.NewsAdapter
import com.example.stock.adapter.RankAdapter
import com.example.stock.adapter.StockAdapter
import com.example.stock.data.News
import com.example.stock.data.Rank

import com.example.stock.databinding.FragmentHomeBinding
import com.example.stock.model.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val mainViewModel by activityViewModels<MainViewModel>()

    private lateinit var stockAdapter: StockAdapter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var rankAdapter: RankAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        stockAdapter = StockAdapter()
        newsAdapter = NewsAdapter()
        rankAdapter = RankAdapter()

        binding.recyclerStock.adapter = stockAdapter
        binding.recyclerNews.adapter = newsAdapter
        binding.recyclerRank.adapter = rankAdapter

        binding.homeFragment = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v("items", "여기들어옴0")


        mainViewModel.myStockList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            val constraintLayout = binding.homeConstraint
            val constraintSet = ConstraintSet()
            constraintSet.clone(constraintLayout)

            // 평상시 recycleview에 대이터가 있어서 있는 만큼 높이를 맞춰준거
            binding.recyclerStockTextEmpty.isVisible = false
            binding.recyclerStock.isVisible = true
            constraintSet.connect(
                binding.homeNews.id,
                ConstraintSet.TOP,
                binding.recyclerStock.id,
                ConstraintSet.BOTTOM,
                10
            )

            stockAdapter.setData(it)

            if (it.isEmpty()) {
                // 소유한 주식이 하나도 없을 떄
                // 주식이 없다는 textview가 뜨고, textview의 높이만큼 밑에 실시간 뉴스도 조정해줘야함
                binding.recyclerStockTextEmpty.isVisible = true
                binding.recyclerStock.isVisible = false

                constraintSet.connect(
                    binding.homeNews.id,
                    ConstraintSet.TOP,
                    binding.recyclerStockTextEmpty.id,
                    ConstraintSet.BOTTOM,
                    10
                )
                constraintSet.applyTo(constraintLayout)

            }
        })

        mainViewModel.newsList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            // 최신순으로 정렬하는 함수
            // 최신순으로 3개만
            var newsList: ArrayList<News> = arrayListOf()
            for (i in 0..2) {
                newsList.add(it[i])
            }
            newsAdapter.setData(newsList)
        })

        mainViewModel.rankList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            // 1,2,3위 순으로 정렬하는 함수
            // 총 3개만 나오게끔
            var rankList: ArrayList<Rank> = arrayListOf()
            for (i in 0..2) {
                rankList.add(it[i])
            }
            rankAdapter.setData(rankList)
        })

    }

    fun goNews(view: View) {
        binding.scrollView.smoothScrollTo(0, binding.recyclerStock.bottom)
        Toast.makeText(activity, "Button Click", Toast.LENGTH_SHORT).show()
    }

    fun goRank(view: View) {
        binding.scrollView.smoothScrollTo(0, binding.recyclerNews.bottom)
        Toast.makeText(activity, "Button Click", Toast.LENGTH_SHORT).show()
    }


    fun newsClick(view: View) {
        view.findNavController().navigate(R.id.action_HomeFragment_to_newsFragment)
    }

    fun rankClick(view: View) {
        view.findNavController().navigate(R.id.action_HomeFragment_to_rankFragment)
    }


    fun search(view: View) {
        view.findNavController().navigate(R.id.action_HomeFragment_to_searchFragment)
    }


    fun IMSI(view: View) {
        view.findNavController().navigate(R.id.action_HomeFragment_to_loginFragment)
    }
}