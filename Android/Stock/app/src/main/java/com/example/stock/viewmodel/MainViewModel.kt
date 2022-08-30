package com.example.stock.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.data.model.*
import com.example.stock.global.GlobalApplication
import com.example.stock.data.repository.StockRepository
import kotlinx.coroutines.*
import java.net.ConnectException

class MainViewModel(private val repository: StockRepository) : ViewModel() {

    // 내 회사 리스트
    private val _myStockList = MutableLiveData<List<Stock>>()
    val myStockList: LiveData<List<Stock>>
        get() = _myStockList

    // 전체 회사 리스트
    private val _stockList = MutableLiveData<List<Stock>>()
    val stockList: LiveData<List<Stock>>
        get() = _stockList

    // 내가 즐겨찾기 한 리스트
    private val _stockFavoriteList = MutableLiveData<List<Stock>>()
    val stockFavoriteList: LiveData<List<Stock>>
        get() = _stockFavoriteList

    // 뉴스 리스트
    private val _newsList = MutableLiveData<ArrayList<News>>()
    val newsList: LiveData<ArrayList<News>>
        get() = _newsList

    // 랭킹 리스트
    private val _rankList = MutableLiveData<ArrayList<Rank>>()
    val rankList: LiveData<ArrayList<Rank>>
        get() = _rankList

    // 회사 리스트
    private val _companyList = MutableLiveData<ArrayList<Company>>()
    val companyList: LiveData<ArrayList<Company>>
        get() = _companyList

    // 유저 객체
    private val _userIam = MutableLiveData<User>()
    val userIam: LiveData<User>
        get() = _userIam

    private val _key = MutableLiveData<String>()
    val key: LiveData<String>
        get() = _key

    private val _stateMessage = MutableLiveData<String>()
    val stateMessage: LiveData<String>
        get() = _stateMessage


    // 각자 초기화용도
    private var news = ArrayList<News>()
    private var stocks = ArrayList<Stock>()
    private var myStocks = ArrayList<Stock>()
    private var rank = ArrayList<Rank>()
    private var company = ArrayList<Company>()

    private var user = User("호민", "s", "d", 10000)

    private lateinit var currentCompany: Company
    private lateinit var curStock: Stock

    init {
        news = arrayListOf(
            News("1", "붕괴하는 한국 경제", "2022.11.14", "", ""),
            News("2", "붕괴하는 일본 경제", "2022.11.13", "", ""),
            News("3", "붕괴하는 미국 경제", "2022.11.12", "", ""),
            News("4", "붕괴하는 미국 경제", "2022.11.11", "", ""),
            News("5", "붕괴하는 미국 경제", "2022.11.10", "", ""),
            News("6", "붕괴하는 미국 경제", "2022.11.09", "", ""),
            News("7", "붕괴하는 미국 경제", "2022.11.08", "", ""),
        )
        _newsList.value = news

        rank = arrayListOf(
            Rank(1, "마동팔", 5, ""),
            Rank(2, "김석기", 2, ""),
            Rank(3, "고생대", 1, ""),
            Rank(4, "신석기", 3, ""),
            Rank(5, "고구려", 4, ""),
            Rank(6, "맜있었", 6, ""),
        )
        rank = ArrayList(rank.sortedBy { it.userRank })

        _rankList.value = rank

        company = arrayListOf(
            Company(
                "1",
                "Apple",
                "애플(영어: Apple Inc.)은 미국 캘리포니아의 아이폰, 아이패드, 애플 워치, 에어팟, 아이맥, 맥북, 맥 스튜디오와 맥 프로, 홈팟 등의 하드웨어와 iOS, iPadOS, macOS 등의 소프트웨어를 설계, 디자인하는 기업이다. 2011년부터 팀 쿡이 CEO를 맡고 있다.",
                10010,
                2002,
                300112,
                40012212
            ),
            Company(
                "2",
                "AmerisourceBergen",
                "AmerisourceBergen Corporation 은 2001년 Bergen Brunswig와 AmeriSource가 합병하여 설립 된 미국 의약품 도매 회사입니다. [2] 이들은 의료 사업 운영 및 환자 서비스와 관련된 의약품 유통 및 컨설팅을 제공합니다. 그들은 또한 브랜드 이름 및 제네릭 의약품 , 처방전 없이 살 수 있는 (OTC) 건강 관리 제품, 가정 건강 관리 용품 및 장비 라인을 급성 치료 병원 및 건강 시스템 을 포함하여 미국 전역의 건강 관리 제공자에게 배포합니다. 소매 약국 , 우편 주문 시설, 의사, 진료소 및 기타 대체 장소 시설, 간호 및 생활 보조 센터. 또한 장기 요양, 산재 보상 및 전문 약물 환자에게 의약품 및 약국 서비스를 제공합니다.",
                1000,
                20,
                12,
                42
            ),
            Company(
                "3",
                "Abbot",
                "대수도원장 ( \"아버지\"를 의미 하는 아람어 Abba 에서 유래)은 기독교 를 포함한 다양한 서양 종교 전통에서 수도원 의 남성 수장 에게 주어진 교회 칭호 입니다. 그 직책 은 수도원장이 아닌 성직자에게 명예 직함으로 주어질 수도 있습니다. 여성에 해당하는 것은 abbess 입니다.",
                101,
                2020,
                3030,
                40
            ),
            Company(
                "4",
                "ADM",
                "일반적으로 ADM 으로 알려진 Archer-Daniels-Midland Company 는 1902년에 설립되었으며 일리노이주 시카고 에 본사를 두고 있는 미국의 다국적 식품 가공 및 상품 무역 회사 입니다. 이 회사는 전 세계적으로 270개 이상의 공장과 420개 이상의 작물 조달 시설을 운영하고 있으며, 곡물 및 유지종자는 전 세계적 으로 식품 , 음료 , 기능 식품 , 산업 및 동물 사료 시장 에서 사용되는 제품으로 가공됩니다 .",
                1,
                2002,
                2332,
                1
            ),
            Company(
                "5",
                "AIG",
                "AIG 라고도 하는 American International Group, Inc. 는 80개 이상의 국가 및 관할 지역에서 사업을 운영하는 미국의 다국적 금융 및 보험 회사입니다. [4] 2019년 1월 1일 기준 AIG 기업의 직원 수는 49,600명이다. [5] 이 회사는 일반 보험, 생명 및 퇴직, 독립형 기술 지원 자회사의 세 가지 핵심 사업을 통해 운영됩니다. [6] [7] [8] 일반 보험에는 상업, 개인 보험, 미국 및 국제 현장 작업이 포함됩니다. 생명 및 퇴직에는 그룹 퇴직, 개인 퇴직, 생명 및 기관 퇴직이 포함됩니다. [6] [7] [8] AIG는 AIG Women's Open(골프)과 New Zealand Rugby(AIG All blacks)의 후원사입니다.",
                100,
                200,
                300,
                400
            )
        )
        _companyList.value = company

        _userIam.value = user

        _stateMessage.value = "200"
    }

    fun getStock(companyId: String): Stock {
        val cp: ArrayList<Stock>? = _stockList.value as ArrayList<Stock>?
        if (cp != null) {
            for (id in cp) {
                if (id.symbol_en == companyId) {
                    curStock = id
                    return id
                }
            }
        }
        return (Stock("콩쥐야", "X됐어", 20000f, 20000f, ".", "", "", 1))
    }

    fun getCompany(companyId: String): Company {
        val cp: ArrayList<Company>? = _companyList.value
        if (cp != null) {
            for (id in cp) {
                if (id.name == companyId) {
                    currentCompany = id
                    return id
                }
            }
        }
        return Company("오류", "", "", 1, 1, 1, 1)
    }

    fun getCurrentCompany(): Company {
        return currentCompany
    }

    fun getCurrentStock(): Stock {
        return curStock
    }

    fun dataCoroutineFun(auth: Auth) {
        getDbStock()
        val mainCoroutineJob = Job()
        CoroutineScope(Dispatchers.IO + mainCoroutineJob).launch {
            // dataTestLoading 통해서
            // 키 유효한지 판별 200이면 유효 / 400이면 만료 / 100이면 네트워크 안되는거
            // dataTestLoading await() 통해서 그전까지 멈춰둠.

            Log.d("items", "dataCoroutineFun 시작!")
            var job = async { dataTestLoading(auth) }
            var code = job.await()

            Log.d("items", "code 값은 = " + code.toString())
            when (code) {

                200 -> dataLoading(auth) // 모든게 정상적인 경우
                400 -> {
                    // 네트워크는 되는데 토큰 문제로 안받아와지는 경우
                    // 1. tockenUpdate() 호출
                    // 2. job으로 묶어서 대기 시킴
                    // 3. 메인 코루틴 중지시키고 dataInitFlow() 재호출
                    Log.d("items", "100 번 들어와서 initTokenSetting() 들어가기 전")
                    var job = launch { tokenUpdate(auth) }
                    job.join()
                    mainCoroutineJob.cancel()
                    if (isActive) {
                        Log.d("items", "코루틴 아직 종료 안됨")
                    }
                    Log.d("items", "dataInitFlow() 재시작")
                    dataCoroutineFun(auth)
                }
                else -> stateErrorNetwork() // 네트워크가 안되는 경우

            }

        }
    }

    private suspend fun tokenUpdate(auth: Auth) {
        // 토큰 받아오기

        try {
            //
            Log.d("items", " tokenUpdate 진입")
            // 1. 키 받아오기 getUserKey
            repository.getUserKey(auth).let { response ->
                Log.d("items::", response.raw().request.url.toString())
                Log.d("items", response.code().toString() + " " + response.message())

                if (response.code() == 200) {
                    //200번이라면 잘 받아와진 것이므로 받아온 데이터를 넣어준다.
                    _key.postValue(response.body())
                    GlobalApplication.key = response.body().toString()
                    Log.d("items", response.body()!!)
                    Log.d("items", "tokenUpdate 완료")
                } else {
                    //200번이 아니라면 불러오지 못한 것이므로, null값 방지용으로 새 객체를 생성해서 넣어준다.
                    Log.d("items", "코루틴 1. 200아님")
                    GlobalApplication.key = "444"
                    _stateMessage.value = "400"
                }

            }
        } catch (e: ConnectException) {

        } catch (e: Exception) {
            Log.d("items", "tokenUpdate() 에러값 : " + e.toString() + "api_exception")
        }


    }

    private suspend fun dataTestLoading(auth: Auth): Int {
        try {
            // 본격적으로 테스트로 하나 받아오기.
            Log.d("items", "dataTestLoading 집입")
            Log.d("items", "GlobalApplication.auth.username = " + GlobalApplication.auth.username)
            Log.d("items", "GlobalApplication.key = " + GlobalApplication.key)
            repository.getMyStockList(
                GlobalApplication.auth.username,
                GlobalApplication.key
            ).let { response ->
                Log.d("items", "dataTestLoading response 완료")
                return if (response.code() == 200) {
                    Log.d("items", "dataTestLoading response 200")
                    200
                } else {
                    Log.d("items", "dataTestLoading response : " + response.code())
                    Log.d("items", "dataTestLoading body : " + response.body()?.symbol)
                    400
                }

            }
        } catch (e: Exception) {
            Log.d("items", "dataTestLoading 에러 Exception")
            return 400
        }

    }

    private suspend fun dataLoading(auth: Auth) {
        // 본격적으로 데이터 받아오기.
        try {
            Log.d("items", "getmyStock 집입 전")
            repository.getMyStockList(
                GlobalApplication.auth.username,
                GlobalApplication.key
            ).let { response ->
                if (response.code() == 200) {
                    Log.d("items", "getmyStock 성공")
                    Log.d("items", "getmyStock 값" + response.body()?.symbol)
                } else {
                    Log.d("items", "getmyStock 집입")
                }
            }
        } catch (e: Exception) {
            Log.d("items", "getmyStock 에러")
        }
        Log.d("items", "getmyStock 집입 후")

        try {
            repository.getMyMoney(GlobalApplication.auth.username, GlobalApplication.key)
            Log.d("items", "getMyMoney 집입 전")
            repository.getMyMoney(GlobalApplication.auth.username, GlobalApplication.key)
                .let { response ->
                    Log.d("items", "getMyMoney 집입")
                    if (response.code() == 200) {
                        Log.d("items", "getMyMoney 성공")
                        Log.d("items", "내돈은 : " + response.body().toString())
                    } else {
                        Log.d("items", "getMyMoney 실패")
                    }

                }
        } catch (e: Exception) {
            Log.d("items", "getMyMoney 에러")
        }
        Log.d("items", "getMyMoney 집입 후")


    }


    private fun stateErrorNetwork() {
        _stateMessage.postValue("서버와의 통신이 불가합니다.")
    }

    private fun stateDataLoading() {
//        _stateMessage.value = "데이터를 서버로부터 받아오고 있습니다."
    }

    fun getDbStock() {
        Log.d("items", "getOwnStock 진입")
        CoroutineScope(Dispatchers.IO).launch {
            repository.getOwnStock().let {
                _myStockList.postValue(it)
            }

            repository.getFavoriteStock().let {
                _stockFavoriteList.postValue(it)
            }

            repository.getAllStock().let {
                _stockList.postValue(it)
            }
        }
    }

    fun favoriteTurnOff(symbol: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("items", "favoriteTurnOFF 들어옴")
            repository.modifyClassification(symbol, 0).let {
                Log.d("items", "favoriteTurnOFF 데이터 바꿈")
                repository.getFavoriteStock().let {
                    _stockFavoriteList.postValue(it)
                }
            }
        }
    }

    fun favoriteTurnOn(symbol: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("items", "favoriteTurnOn 들어옴")
            repository.modifyClassification(symbol, 2).let {
                Log.d("items", "favoriteTurnOn 데이터 바꿈")
                repository.getFavoriteStock().let {
                    _stockFavoriteList.postValue(it)
                }
            }
        }
    }


}


