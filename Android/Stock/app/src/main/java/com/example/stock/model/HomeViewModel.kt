package com.example.stock.model

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.stock.R
import com.example.stock.data.Event
import com.example.stock.data.Stock
import com.example.stock.data.retrofit.RetroAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.ConnectException

class HomeViewModel(private val repository: RetroAPIRepository) : ViewModel() {
    // 버튼 클릭 변수
    private val _newsBtnClick = MutableLiveData<Event<Boolean>>()
    val newsBtnClick: LiveData<Event<Boolean>>
        get() = _newsBtnClick

    private val _rankBtnClick = MutableLiveData<Event<Boolean>>()
    val rankBtnClick: LiveData<Event<Boolean>>
        get() = _rankBtnClick

    private val _searchBtnClick = MutableLiveData<Event<Boolean>>()
    val searchBtnClick: LiveData<Event<Boolean>>
        get() = _searchBtnClick

    private val _newsGoBtnClick = MutableLiveData<Event<Boolean>>()
    val newsGoBtnClick: LiveData<Event<Boolean>>
        get() = _newsGoBtnClick

    private val _rankGoBtnClick = MutableLiveData<Event<Boolean>>()
    val rankGoBtnClick: LiveData<Event<Boolean>>
        get() = _rankGoBtnClick

    private val _myStockGoBtnClick = MutableLiveData<Event<Boolean>>()
    val myStockGoBtnClick: LiveData<Event<Boolean>>
        get() = _myStockGoBtnClick

    private val _isMainBtnClick = MutableLiveData<Event<Boolean>>()
    val isMainBtnClick: LiveData<Event<Boolean>>
        get() = _isMainBtnClick

    // Retro용 변수
    private val _myStockList = MutableLiveData<Stock>()
    val myStockList: LiveData<Stock>
        get() = _myStockList

    fun search() {
        _searchBtnClick.value = Event(true)
    }

    fun myStockGoClick() {
        _myStockGoBtnClick.value = Event(true)
    }

    fun rankGoClick() {
        _rankGoBtnClick.value = Event(true)
    }

    fun newsGoClick() {
        _newsGoBtnClick.value = Event(true)
    }

    fun mainBtnClick() {
        _isMainBtnClick.value = Event(true)
    }

    fun newsClick() {
        _newsBtnClick.value = Event(true)
    }

    fun rankClick() {
        _rankBtnClick.value = Event(true)
    }

    fun IMSI() {
    }



    fun getMyStockList(key :String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.getMyStockList("s").let {
                        response ->
                    Log.d("api_request_url::", response.raw().request.url.toString())
                    Log.d("get_user_api", response.code().toString() + " " + response.message())

                    if(response.code() == 200) {
                        //200번이라면 잘 받아와진 것이므로 받아온 데이터를 넣어준다.
                        response.body()?.code = response.code()

                        _myStockList.postValue(response.body())
                        Log.d("api_success", response.body().toString())
                    } else {
                        //200번이 아니라면 불러오지 못한 것이므로, null값 방지용으로 새 객체를 생성해서 넣어준다.
                        _myStockList.postValue(Stock(
                            "1",
                            "1",
                            "",
                            "",
                            "0",
                            "0",
                            0
                        ).apply { code = response.code() }
                        )
                    }

                }
            } catch (e: ConnectException) {
                e.printStackTrace()
                Log.d("api_exception", e.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("api_exception", e.toString())
            }
        }
    }
}