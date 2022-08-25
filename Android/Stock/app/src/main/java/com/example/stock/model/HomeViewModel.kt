package com.example.stock.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.util.Event
import com.example.stock.data.model.Stock
import com.example.stock.data.repository.StockRepository

class HomeViewModel(private val repository: StockRepository) : ViewModel() {
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
    private val _myStockList = MutableLiveData<List<Stock>>()
    val myStockList: LiveData<List<Stock>>
        get() = _myStockList

    private val _IMSI = MutableLiveData<Event<Boolean>>()
    val IMSI: LiveData<Event<Boolean>>
        get() = _IMSI

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
        _IMSI.value = Event(true)
    }


    fun getMyStockList(username: String, key: String) {

//        val call = GlobalApplication.baseService.create(RetroAPI::class.java)
//            .getMyStockList(username, key)
//        call.enqueue(object : Callback<Stock> {
//            override fun onResponse(
//                call: Call<Stock>,
//                response: Response<Stock>
//            ) {
//                Log.v("items", "StockList 받는 함수임.")
//                Log.v(
//                    "items",
//                    "Response code : " + response.code().toString() + " " + response.message()
//                )
//                if (response.isSuccessful) {
//                    var key = response.body()!!
//                    Log.v("items", "getMyStockList 의 심지어 성공적임")
//                }
//
//            }
//
//            override fun onFailure(call: Call<Stock>, t: Throwable) {
//                Log.v("items", " getMyStockList의 Failure : " + t)
//            }
//
//        })

    }
}
//    fun getMyStockList(key: String) {
//        Log.v("items", "getMyStockList의 키는 = " + key)
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                repository.getMyStockList(key).let { response ->
//                    Log.v("items", "일단 들어옴1" + response.raw().request.url.toString())
//                    Log.v("items", "일단 들어옴2" + response.code().toString() + " " + response.message())
//
//                    if (response.code() == 200) {
//                        //200번이라면 잘 받아와진 것이므로 받아온 데이터를 넣어준다.
//                        _myStockList.postValue(response.body())
//                        Log.v("items", "성공" + response.body().toString())
//                    } else {
//                        Log.v("items", "실패")
//                        //200번이 아니라면 불러오지 못한 것이므로, null값 방지용으로 새 객체를 생성해서 넣어준다.
//                        _myStockList.postValue(
//                            Stock(
//                                "1",
//                                "1",
//                                "",
//                                "",
//                                "0",
//                                "0",
//                                0
//                            )
//                        )
//                    }
//
//                }
//            } catch (e: ConnectException) {
//                e.printStackTrace()
//                Log.v("items", "연결 불능" + e.toString() + "api_exception")
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Log.v("items", "예외 처리" + e.toString() + "api_exception")
//            }
//        }
//    }



