package com.example.stock.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.data.News

class NewsViewModel() : ViewModel() {

    private val _newsList = MutableLiveData<ArrayList<News>>()
    val newsList: LiveData<ArrayList<News>>
        get() = _newsList

    private var items = ArrayList<News>()

    init {
        items = arrayListOf(
            News("1", "homin", "100000", "", ""),
        )
        _newsList.value = items
    }

    fun buttonClick() {
        val range = (1000..10000)
        val user = News("1", "homin", range.random().toString(), "", "")
        items.add(user)

        Log.v("변하는 List", "크기는 몇이여? " + items.size)
        for (i in items) {
            Log.v("tag1", i.title + " " + i.date)
        }
        _newsList.postValue(items)

        for (i in newsList.value!!) {
            Log.v("안변하는 List", i.title + " " + i.date)
        }

    }
}