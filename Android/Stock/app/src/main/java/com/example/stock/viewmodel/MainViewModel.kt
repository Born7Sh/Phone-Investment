package com.example.stock.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.data.model.*
import com.example.stock.global.GlobalApplication
import com.example.stock.data.repository.StockRepository
import com.example.stock.data.retrofit.handleApi
import com.example.stock.util.ApiError
import com.example.stock.util.ApiResult
import com.example.stock.util.ExceptionError
import com.example.stock.util.Success
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import java.net.ConnectException

class MainViewModel(private val repository: StockRepository) : ViewModel() {

    // 뉴스 리스트
    private val _newsList = MutableLiveData<ArrayList<News>>()
    val newsList: LiveData<ArrayList<News>>
        get() = _newsList

    // 랭킹 리스트
    private val _rankList = MutableLiveData<ArrayList<Rank>>()
    val rankList: LiveData<ArrayList<Rank>>
        get() = _rankList

//    // 회사 리스트
//    private val _companyList = MutableLiveData<ArrayList<Company>>()
//    val companyList: LiveData<ArrayList<Company>>
//        get() = _companyList

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
    private var rank = ArrayList<Rank>()

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

//        company = arrayListOf(
//            // company 예시니깐 날려도 됨.
//            Company(
//                "1",
//                "Apple",
//                "애플(영어: Apple Inc.)은 미국 캘리포니아의 아이폰, 아이패드, 애플 워치, 에어팟, 아이맥, 맥북, 맥 스튜디오와 맥 프로, 홈팟 등의 하드웨어와 iOS, iPadOS, macOS 등의 소프트웨어를 설계, 디자인하는 기업이다. 2011년부터 팀 쿡이 CEO를 맡고 있다.",
//                10010,
//                2002,
//                300112,
//                40012212
//            ),
//            Company(
//                "2",
//                "AmerisourceBergen",
//                "AmerisourceBergen Corporation 은 2001년 Bergen Brunswig와 AmeriSource가 합병하여 설립 된 미국 의약품 도매 회사입니다. [2] 이들은 의료 사업 운영 및 환자 서비스와 관련된 의약품 유통 및 컨설팅을 제공합니다. 그들은 또한 브랜드 이름 및 제네릭 의약품 , 처방전 없이 살 수 있는 (OTC) 건강 관리 제품, 가정 건강 관리 용품 및 장비 라인을 급성 치료 병원 및 건강 시스템 을 포함하여 미국 전역의 건강 관리 제공자에게 배포합니다. 소매 약국 , 우편 주문 시설, 의사, 진료소 및 기타 대체 장소 시설, 간호 및 생활 보조 센터. 또한 장기 요양, 산재 보상 및 전문 약물 환자에게 의약품 및 약국 서비스를 제공합니다.",
//                1000,
//                20,
//                12,
//                42
//            ),
//            Company(
//                "3",
//                "Abbot",
//                "대수도원장 ( \"아버지\"를 의미 하는 아람어 Abba 에서 유래)은 기독교 를 포함한 다양한 서양 종교 전통에서 수도원 의 남성 수장 에게 주어진 교회 칭호 입니다. 그 직책 은 수도원장이 아닌 성직자에게 명예 직함으로 주어질 수도 있습니다. 여성에 해당하는 것은 abbess 입니다.",
//                101,
//                2020,
//                3030,
//                40
//            ),
//            Company(
//                "4",
//                "ADM",
//                "일반적으로 ADM 으로 알려진 Archer-Daniels-Midland Company 는 1902년에 설립되었으며 일리노이주 시카고 에 본사를 두고 있는 미국의 다국적 식품 가공 및 상품 무역 회사 입니다. 이 회사는 전 세계적으로 270개 이상의 공장과 420개 이상의 작물 조달 시설을 운영하고 있으며, 곡물 및 유지종자는 전 세계적 으로 식품 , 음료 , 기능 식품 , 산업 및 동물 사료 시장 에서 사용되는 제품으로 가공됩니다 .",
//                1,
//                2002,
//                2332,
//                1
//            ),
//            Company(
//                "5",
//                "AIG",
//                "AIG 라고도 하는 American International Group, Inc. 는 80개 이상의 국가 및 관할 지역에서 사업을 운영하는 미국의 다국적 금융 및 보험 회사입니다. [4] 2019년 1월 1일 기준 AIG 기업의 직원 수는 49,600명이다. [5] 이 회사는 일반 보험, 생명 및 퇴직, 독립형 기술 지원 자회사의 세 가지 핵심 사업을 통해 운영됩니다. [6] [7] [8] 일반 보험에는 상업, 개인 보험, 미국 및 국제 현장 작업이 포함됩니다. 생명 및 퇴직에는 그룹 퇴직, 개인 퇴직, 생명 및 기관 퇴직이 포함됩니다. [6] [7] [8] AIG는 AIG Women's Open(골프)과 New Zealand Rugby(AIG All blacks)의 후원사입니다.",
//                100,
//                200,
//                300,
//                400
//            )
//        )
//        _companyList.value = company

        _userIam.value = user

        _stateMessage.value = "200"
    }


    private fun stateErrorNetwork() {
        _stateMessage.postValue("서버와의 통신이 불가합니다.")
    }

    fun dataUpdate() {
        Log.d("items", "dataUpdate 진입")
        CoroutineScope(Dispatchers.Main).launch {
            // 22/09/01
            // Event Wrapper class 생성 APIResult / handleApi
            // https://bb-library.tistory.com/264
            // in/out 이해 https://hungseong.tistory.com/30

            val result: ApiResult<List<Stock>> = handleApi({
                repository.getStockList(
                    GlobalApplication.auth.username,
                    GlobalApplication.key
                )
            })
            Log.d("items", "dataUpdate 결과")
            when (result) {
                is Success -> {
                    // result.data will give you ResponseBody
                    Log.d("items", "dataUpdate 성공")
                    for (stock in result.data) {
                        Log.d("items", "dataUpdate stock 이름 "+ stock.symbol + " 수정 가격 : " + stock.price.toString())
                        repository.modifyPrice(stock.symbol, stock.price)
                    }
                    _stateMessage.postValue("200")
                    Log.d("items", "dataUpdate delay 30초")
                    delay(30000L)
                    Log.d("items", "dataUpdate delay 30초 끝")
                    cancel()
                    Log.d("items", "dataUpdate 반복 시작")
                    dataUpdate()
                }
                is ApiError -> {
                    // result.exception will provide the error
                    Log.d("items", "dataUpdate 실패")
                    Log.d("items", "에러입니다. : "+result.exception)

                    Log.d("items", "dataUpdate delay 30초")
                    delay(30000L)
                    Log.d("items", "dataUpdate delay 30초 끝")

                    cancel()
                    dataUpdate()
                }

                is ExceptionError -> {
                    Log.d("items", "dataUpdate ExceptionError 에러임")
                    cancel()

                    Log.d("items", "dataUpdate delay 30초")
                    delay(30000L)
                    Log.d("items", "dataUpdate delay 30초 끝")

                    dataUpdate()
                }
            }
        }


    }
}





