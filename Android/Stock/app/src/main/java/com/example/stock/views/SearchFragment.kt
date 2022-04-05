package com.example.stock.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.stock.R
import com.example.stock.databinding.FragmentSearchBinding
import com.mancj.materialsearchbar.MaterialSearchBar

class SearchFragment : Fragment() {

    lateinit var binding : FragmentSearchBinding
    lateinit var adapter : ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)


        //리스트뷰 초기에 안보이게 설정

        return binding.root
    }

    fun setSearchbar(){
        binding.searchBar.setHint("Search")
        binding.searchBar.setSpeechMode(false)
        binding.searchBar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener{
            override fun onButtonClicked(buttonCode: Int) {
                TODO("Not yet implemented")
            }
            //검색창 누른 상태 여부 확인
            override fun onSearchStateChanged(enabled: Boolean) {
                //맞으면 리스트뷰 보이게 설정
                if(enabled){
                    binding.mListView.visibility = View.VISIBLE
                }else{ //아니면 안 보이게
                    binding.mListView.visibility = View.INVISIBLE
                }
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                TODO("Not yet implemented")
            }

        })

        binding.searchBar.addTextChangeListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            //검색어 변경하면 ListView 내용 변경
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.getFilter().filter(s)
            }

        })
    }

    fun setListView(){
        binding.mListView.visibility = View.INVISIBLE
        //SearchBar와 ListView 연동
        binding.mListView.setAdapter(adapter)


        //ListView 내의 아이템 누르면 Toast 발생
        binding.mListView.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(activity, adapter.getItem(position)!!.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }


}