package com.example.stock.views.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.stock.R
import com.example.stock.adapter.NewsAdapter
import com.example.stock.adapter.RankAdapter
import com.example.stock.adapter.StockAdapter
import com.example.stock.data.EventObserver
import com.example.stock.data.News
import com.example.stock.data.Rank

import com.example.stock.databinding.FragmentHomeBinding
import com.example.stock.model.HomeViewModel
import com.example.stock.model.MainViewModel
import com.example.stock.model.StockDetailViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val mainViewModel by activityViewModels<MainViewModel>()
    lateinit var homeViewModel: HomeViewModel


    private lateinit var stockAdapter: StockAdapter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var rankAdapter: RankAdapter
    private var waitTime = 0L
    private var isFabOpen = false // 이동 제어용 변수

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        stockAdapter = StockAdapter()
        newsAdapter = NewsAdapter()
        rankAdapter = RankAdapter()

        binding.recyclerStock.adapter = stockAdapter
        binding.recyclerNews.adapter = newsAdapter
        binding.recyclerRank.adapter = rankAdapter
        binding.viewModel = homeViewModel

        binding.homeFragment = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        homeViewModel.searchBtnClick.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_HomeFragment_to_searchFragment)
        })

        homeViewModel.newsBtnClick.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_HomeFragment_to_newsFragment)
        })

        homeViewModel.rankBtnClick.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_HomeFragment_to_rankFragment)
        })

        homeViewModel.isMainBtnClick.observe(viewLifecycleOwner, EventObserver {

            if (isFabOpen) {
                ObjectAnimator.ofFloat(binding.fabStoke, "translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(binding.fabNews, "translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(binding.fabRank, "translationY", 0f).apply { start() }
                ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 90f, 0f).apply { start() }
            } else {
                ObjectAnimator.ofFloat(binding.fabStoke, "translationY", -540f).apply { start() }
                ObjectAnimator.ofFloat(binding.fabNews, "translationY", -360f).apply { start() }
                ObjectAnimator.ofFloat(binding.fabRank, "translationY", -180f).apply { start() }
                ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 0f, 90f).apply { start() }
            }

            isFabOpen = !isFabOpen
        })

        homeViewModel.myStockGoBtnClick.observe(viewLifecycleOwner, EventObserver {
            binding.scrollView.smoothScrollTo(0, binding.homeOwnStock.top)
            Toast.makeText(activity, "Button Click", Toast.LENGTH_SHORT).show()
        })
        homeViewModel.newsGoBtnClick.observe(viewLifecycleOwner, EventObserver {
            binding.scrollView.smoothScrollTo(0, binding.recyclerStock.bottom)
            Toast.makeText(activity, "Button Click", Toast.LENGTH_SHORT).show()
        })
        homeViewModel.rankGoBtnClick.observe(viewLifecycleOwner, EventObserver {
            binding.scrollView.smoothScrollTo(0, binding.recyclerNews.bottom)
            Toast.makeText(activity, "Button Click", Toast.LENGTH_SHORT).show()
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback will only be called when MyFragment is at least Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (System.currentTimeMillis() - waitTime >= 1500) {
                waitTime = System.currentTimeMillis()
                Toast.makeText(context, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            } else {
                activity?.finish() // 액티비티 종료
            }
        }

        // The callback can be enabled or disabled here or in the lambda
    }

    private fun initViewModel(){

    }
}