package com.example.stock.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.stock.R
import com.example.stock.adapter.SearchListAdapter
import com.example.stock.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    // 굳이 viewModel 써야할 이유가 있나?
    // 데이터 검색하는거 빼곤 안쓸거 같은데 그냥 데이터 바인딩만 하자.

    lateinit var binding: FragmentSearchBinding
    private lateinit var searchListAdapter: SearchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        searchListAdapter = SearchListAdapter()

        binding.recyclerSearch.adapter = searchListAdapter
        //리스트뷰 초기에 안보이게 설정
        binding.editSearch.addTextChangedListener(search())
        return binding.root
    }

    fun search() : TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                searchListAdapter.getFilter().filter(s);
            }
            override fun afterTextChanged(s: Editable) {

            }
        }
    }
}




