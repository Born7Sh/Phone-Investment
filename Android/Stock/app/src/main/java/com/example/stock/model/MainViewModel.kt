package com.example.stock.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.R
import com.example.stock.data.*

class MainViewModel() : ViewModel() {

    // 내 회사 리스트
    private val _myStockList = MutableLiveData<ArrayList<Stock>>()
    val myStockList: LiveData<ArrayList<Stock>>
        get() = _myStockList

    // 전체 회사 리스트
    private val _stockList = MutableLiveData<ArrayList<Stock>>()
    val stockList: LiveData<ArrayList<Stock>>
        get() = _stockList

    // 내가 즐겨찾기 한 리스트
    private val _stockFavoriteList = MutableLiveData<ArrayList<Stock>>()
    val stockFavoriteList: LiveData<ArrayList<Stock>>
        get() = _stockFavoriteList

    // 뉴스 리스트
    private val _newsList = MutableLiveData<ArrayList<News>>()
    val newsList: LiveData<ArrayList<News>>
        get() = _newsList

    // 유저 리스트
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


    // 각자 초기화용도
    private var news = ArrayList<News>()
    private var stocks = ArrayList<Stock>()
    private var myStocks = ArrayList<Stock>()
    private var rank = ArrayList<Rank>()
    private var company = ArrayList<Company>()


    private var user = User("호민", 100000)

    private lateinit var currentCompany: Company
    private lateinit var curStock: Stock

    init {
        stocks = arrayListOf(
            Stock("1", "Apple", "AAPL", "12345", "-3.9", R.drawable.c_aapl),
            Stock("2", "AmerisourceBergen", "ABC", "12345", "-3.9", R.drawable.c_abc),
            Stock("3", "Abbot", "ABT", "12121", "+3.4", R.drawable.c_abt),
            Stock("4", "ADM", "ADM", "12121", "+3.4", R.drawable.c_adm),
            Stock("5", "AIG", "AIG", "12121", "+3.4", R.drawable.c_alg),
            Stock("6", "AMD", "AMD", "234441", "+3.7", R.drawable.c_amd),
            Stock("7", "Amazon", "AMZN", "234441", "+3.7", R.drawable.c_amzn),
            Stock("8", "Anthem", "ANTM", "12345", "+3.1", R.drawable.c_antm),
            Stock("9", "American Express", "AXP", "11222", "+3.2", R.drawable.c_axp),
            Stock("10", "BOEING", "BA", "11222", "+3.2", R.drawable.c_ba),
            Stock("11", "Bank of America", "BAC", "11222", "+3.2", R.drawable.c_bac),
            Stock("12", "BEST BUY", "BBY", "11222", "+3.2", R.drawable.c_bby),
            Stock("13", "CITI", "C", "11222", "+3.2", R.drawable.c_c),
            Stock("14", "CardinalHealth", "CAH", "11222", "+3.2", R.drawable.c_cah),
            Stock("15", "CAT", "CAT", "11222", "+3.2", R.drawable.c_cat),
            Stock("16", "chico's", "CHS", "11222", "+3.2", R.drawable.c_chs),
            Stock("17", "COMCAST", "CMCSA", "11222", "+3.2", R.drawable.c_cmcsa),
            Stock("18", "ConocoPhillips", "COP", "11222", "+3.2", R.drawable.c_cop),
            Stock("19", "Costoco", "COST", "11222", "+3.2", R.drawable.c_cost),
            Stock("20", "coupang", "CPNG", "11222", "+3.2", R.drawable.c_cpng),
            Stock("21", "cisco", "CSCO", "11222", "+3.2", R.drawable.c_csco),
            Stock("22", "CVSHealth", "CVS", "11222", "+3.2", R.drawable.c_cvs),
            Stock("23", "Chevron", "CVX", "11222", "+3.2", R.drawable.c_cvx),
            Stock("24", "DELTA", "DAL", "11222", "+3.2", R.drawable.c_dal),
            Stock("25", "DUPONT", "DD", "11222", "+3.2", R.drawable.c_dd),
            Stock("26", "DELL", "DELL", "11222", "+3.2", R.drawable.c_dell),
            Stock("27", "Walt Disney", "DIS", "11222", "+3.2", R.drawable.c_dis),
            Stock("28", "Fored", "F", "11222", "+3.2", R.drawable.c_f),
            Stock("29", "Meta", "FB", "11222", "+3.2", R.drawable.c_fb),
            Stock("30", "FedEx", "FDX", "11222", "+3.2", R.drawable.c_fdx),
            Stock("31", "GENERAL DYNAMICS", "GD", "11222", "+3.2", R.drawable.c_gd),
            Stock("32", "General Electric", "GE", "11222", "+3.2", R.drawable.c_ge),
            Stock("33", "CHEVROLET", "GM", "11222", "+3.2", R.drawable.c_gm),
            Stock("34", "Google", "GOOG", "11222", "+3.2", R.drawable.c_goog),
            Stock("35", "GoldmanSachs", "GS", "11222", "+3.2", R.drawable.c_gs),
            Stock("36", "HCA", "HCA", "11222", "+3.2", R.drawable.c_hca),
            Stock("37", "THE HOME DEPOT", "HD", "11222", "+3.2", R.drawable.c_hd),
            Stock("38", "HESS", "HES", "11222", "+3.2", R.drawable.c_hes),
            Stock("39", "THE HARTFORD", "HIG", "11222", "+3.2", R.drawable.c_hig),
            Stock("40", "Honeywell", "HON", "11222", "+3.2", R.drawable.c_hon),
            Stock("41", "HP", "HPQ", "11222", "+3.2", R.drawable.c_hpq),
            Stock("42", "IBM", "IBM", "11222", "+3.2", R.drawable.c_ibm),
            Stock("43", "intel", "INTC", "11222", "+3.2", R.drawable.c_intc),
            Stock("44", "Johnson Controls", "JCL", "11222", "+3.2", R.drawable.c_jcl),
            Stock("45", "Johnson & Johnson", "JNJ", "11222", "+3.2", R.drawable.c_jnj),
            Stock("46", "J.P.Morgan", "JPM", "11222", "+3.2", R.drawable.c_jpm),
            Stock("47", "CocaCola", "KO", "11222", "+3.2", R.drawable.c_ko),
            Stock("48", "Kroger", "KR", "11222", "+3.2", R.drawable.c_kr),
            Stock("49", "LIBERTY GLOBAL", "LBTYA", "11222", "+3.2", R.drawable.c_lbtya),
            Stock("50", "LOCKHED MARTIN", "LMT", "11222", "+3.2", R.drawable.c_lmt),
            Stock("51", "MCKESSON", "MCK", "11222", "+3.2", R.drawable.c_mck),
            Stock("52", "MetLife", "MET", "11222", "+3.2", R.drawable.c_met),
            Stock("53", "MERCK", "MRK", "11222", "+3.2", R.drawable.c_mrk),
            Stock("54", "Marathon OIL", "MRO", "11222", "+3.2", R.drawable.c_mro),
            Stock("55", "Morgan Stanley", "MS", "11222", "+3.2", R.drawable.c_ms),
            Stock("56", "Microsoft", "MSFT", "11222", "+3.2", R.drawable.c_msft),
            Stock("57", "NORTHROP GRUMMAN", "NOC", "11222", "+3.2", R.drawable.c_noc),
            Stock("58", "NVIDIA", "NVDA", "11222", "+3.2", R.drawable.c_nvda),
            Stock("59", "PEPSI", "PEP", "11222", "+3.2", R.drawable.c_pep),
            Stock("60", "PFIZER", "PFE", "11222", "+3.2", R.drawable.c_pfe),
            Stock("61", "P&G", "PG", "11222", "+3.2", R.drawable.c_pg),
            Stock("62", "PHOLIP MORRIS", "PM", "11222", "+3.2", R.drawable.c_pm),
            Stock("63", "Invesco", "QQQ", "11222", "+3.2", R.drawable.c_qqq),
            Stock("64", "RITE AID", "RAD", "11222", "+3.2", R.drawable.c_rad),
            Stock("65", "Raytheon", "RTX", "11222", "+3.2", R.drawable.c_rtx),
            Stock("66", "STATE STREET", "SPY", "11222", "+3.2", R.drawable.c_spy),
            Stock("67", "Sysco", "SYY", "11222", "+3.2", R.drawable.c_syy),
            Stock("68", "AT&T", "T", "11222", "+3.2", R.drawable.c_t),
            Stock("69", "TARGET", "TGT", "11222", "+3.2", R.drawable.c_tgt),
            Stock("70", "TRAVELERS", "TRV", "11222", "+3.2", R.drawable.c_trv),
            Stock("71", "TESLA", "TSLA", "11222", "+3.2", R.drawable.c_tsla),
            Stock("72", "UnitedHealth Group", "UNH", "11222", "+3.2", R.drawable.c_unh),
            Stock("73", "ups", "UPS", "11222", "+3.2", R.drawable.c_ups),
            Stock("74", "VALERO", "VLO", "11222", "+3.2", R.drawable.c_vlo),
            Stock("75", "verizon", "VZ", "11222", "+3.2", R.drawable.c_vz),
            Stock("76", "Alliance Boots", "WBA", "11222", "+3.2", R.drawable.c_wba),
            Stock("77", "WELLS FARGO", "WFC", "11222", "+3.2", R.drawable.c_wfc),
            Stock("78", "Walmart", "WMT", "11222", "+3.2", R.drawable.c_wmt),
            Stock("79", "ExxonMobil", "XOM", "11222", "+3.2", R.drawable.c_xom),
        )
        _stockList.value = stocks

        myStocks = arrayListOf(
            Stock("1", "Apple", "AAPL", "12345", "-3.9", R.drawable.c_aapl),
            Stock("2", "AmerisourceBergen", "ABC", "12345", "-3.9", R.drawable.c_abc),
            Stock("3", "Abbot", "ABT", "12121", "+3.4", R.drawable.c_abt),
            Stock("4", "ADM", "ADM", "12121", "+3.4", R.drawable.c_adm),
            Stock("5", "AIG", "AIG", "12121", "+3.4", R.drawable.c_alg)
        )
        _myStockList.value = myStocks


        stocks = arrayListOf(
            Stock("1", "Apple", "AAPL", "12345", "-3.9", R.drawable.c_aapl),
            Stock("2", "AmerisourceBergen", "ABC", "12345", "-3.9", R.drawable.c_abc),
            Stock("3", "Abbot", "ABT", "12121", "+3.4", R.drawable.c_abt),
            Stock("4", "ADM", "ADM", "12121", "+3.4", R.drawable.c_adm),
            Stock("5", "AIG", "AIG", "12121", "+3.4", R.drawable.c_alg)
        )
        _stockFavoriteList.value = stocks


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
    }

    fun getStock(companyId: String): Stock {
        val cp: ArrayList<Stock>? = _stockList.value
        if (cp != null) {
            for (id in cp) {
                if (id.name == companyId) {
                    curStock = id
                    return id
                }
            }
        }
        return (Stock("콩쥐야", "X됐어", ".", ".", ".", 1))
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
}