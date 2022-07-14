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


    // 각자 초기화용도
    private var news = ArrayList<News>()
    private var stocks = ArrayList<Stock>()
    private var myStocks = ArrayList<Stock>()
    private var rank = ArrayList<Rank>()
    private var company = ArrayList<Company>()


    private var user = User("호민", "s","d",10000)

    private lateinit var currentCompany: Company
    private lateinit var curStock: Stock

    init {
        stocks = arrayListOf(
            Stock("AAPL", "", "10000", "100", "애플", "Apple", R.drawable.c_aapl),
            Stock("ABC", "", "20000", "200", "아메리소스베르겐", "AmerisourceBergen", R.drawable.c_abc),
            Stock("ABT", "", "30000", "300", "애벗", "Abbot", R.drawable.c_abt),
            Stock("ADM", "", "40000", "400", "아처 대니얼스 미들랜드", "ADM", R.drawable.c_adm),
            Stock("AIG", "", "50000", "500", "아메리칸 인터내셔널 그룹", "AIG", R.drawable.c_alg),
            Stock("AMD", "", "60000", "600", "암드", "+AMD", R.drawable.c_amd),
            Stock("AMZN", "", "70000", "600", "아마존", "Amazon", R.drawable.c_amzn),
            Stock("ANTM", "", "80000", "600", "앤썸", "Anthem", R.drawable.c_antm),
            Stock("AXP", "", "90000", "800", "아메리칸 익스프레스", "American Express", R.drawable.c_axp),
            Stock("BA", "", "10000", "200", "보잉", "BOEING", R.drawable.c_ba),
            Stock("BAC", "", "20000", "230", "뱅크 오브 아메리카", "Bank of America", R.drawable.c_bac),
            Stock("BBY", "", "20030", "350", "베스트 바이", "BEST BUY", R.drawable.c_bby),
            Stock("C", "", "20040", "230", "씨티그룹", "CITI", R.drawable.c_c),
            Stock("CAH", "", "20050", "500", "카디널헬스", "CardinalHealth", R.drawable.c_cah),
            Stock("CAT", "", "20060", "650", "캐터필러", "CAT", R.drawable.c_cat),
            Stock("CHS", "", "20070", "700", "chico's", "chico's", R.drawable.c_chs),
            Stock("CMCSA", "", "20080", "800", "컴캐스트", "COMCAST", R.drawable.c_cmcsa),
            Stock("COP", "", "20080", "700", "코노코필립스 ", "ConocoPhillips", R.drawable.c_cop),
            Stock("COST", "", "20080", "700", "코스트코", "Costoco", R.drawable.c_cost),
            Stock("CPNG", "", "20080", "700", "쿠팡", "coupang", R.drawable.c_cpng),
            Stock("CSCO", "", "20080", "700", "시스코", "cisco", R.drawable.c_csco),
            Stock("CVS", "", "20080", "700", "CVS 케어마크", "CVSHealth", R.drawable.c_cvs),
            Stock("CVX", "", "20080", "700", "셰브론", "Chevron", R.drawable.c_cvx),
            Stock("DAL", "", "20080", "700", "델타", "DELTA", R.drawable.c_dal),
            Stock("DD", "", "20080", "700", "듀폰", "DUPONT", R.drawable.c_dd),
            Stock("DELL", "", "20080", "700", "델", "DELL", R.drawable.c_dell),
            Stock("DIS", "", "20080", "700", "디지니", "Walt Disney", R.drawable.c_dis),
            Stock("F", "", "20080", "700", "포드", "Fored", R.drawable.c_f),
            Stock("FB", "", "20080", "700", "메타", "Meta", R.drawable.c_fb),
            Stock("FDX", "", "20080", "700", "페덱스", "FedEx", R.drawable.c_fdx),
            Stock("GD", "", "20080", "700", "제너럴 다이내믹스", "GENERAL DYNAMICS", R.drawable.c_gd),
            Stock("GE", "", "20080", "700", "제너럴 일렉트릭", "General Electric", R.drawable.c_ge),
            Stock("GM", "", "20080", "700", "쉐보레", "CHEVROLET", R.drawable.c_gm),
            Stock("GOOG", "", "20080", "700", "구글", "Google", R.drawable.c_goog),
            Stock("GS", "", "20080", "700", "골드만삭스", "GoldmanSachs", R.drawable.c_gs),
            Stock("HCA", "", "20080", "700", "미국 병원 법인", "HCA", R.drawable.c_hca),
            Stock("HD", "", "20080", "700", "홈디포", "THE HOME DEPOT", R.drawable.c_hd),
            Stock("HES", "", "20080", "700", "헤스 코퍼레이션", "HESS", R.drawable.c_hes),
            Stock("HIG", "", "20080", "700", "하트퍼드", "THE HARTFORD", R.drawable.c_hig),
            Stock("HON", "", "20080", "700", "허니웰", "Honeywell", R.drawable.c_hon),
            Stock("HPQ", "", "20080", "700", "휴렛 팩커드", "HP", R.drawable.c_hpq),
            Stock("IBM", "", "20080", "700", "IBM", "IBM", R.drawable.c_ibm),
            Stock("INTC", "", "20080", "700", "인텔", "intel", R.drawable.c_intc),
            Stock("JCL", "", "20080", "700", "존슨컨트롤즈", "Johnson Controls", R.drawable.c_jcl),
            Stock("JNJ", "", "20080", "700", "존슨앤드존슨", "Johnson & Johnson", R.drawable.c_jnj),
            Stock("JPM", "", "20080", "700", "JP모간", "J.P.Morgan", R.drawable.c_jpm),
            Stock("KO", "", "20080", "700", "코카콜라", "CocaCola", R.drawable.c_ko),
            Stock("KR", "", "20080", "700", "크로거", "Kroger", R.drawable.c_kr),
            Stock("LBTYA", "", "20080", "700", "리버티 글로벌", "LIBERTY GLOBAL", R.drawable.c_lbtya),
            Stock("LMT", "", "20080", "700", "록히드 마틴", "LOCKHED MARTIN", R.drawable.c_lmt),
            Stock("MCK", "", "20080", "700", "MCKESSON", "MCKESSON", R.drawable.c_mck),
            Stock("MET", "", "20080", "700", "메트라이프", "MetLife", R.drawable.c_met),
            Stock("MRK", "", "20080", "700", "머크 그룹", "MERCK", R.drawable.c_mrk),
            Stock("MRO", "", "20080", "700", "마라톤 오일", "Marathon OIL", R.drawable.c_mro),
            Stock("MS", "", "20080", "700", "모건 스탠리", "Morgan Stanley", R.drawable.c_ms),
            Stock("MSFT", "", "20080", "700", "마이크로소프트", "Microsoft", R.drawable.c_msft),
            Stock("NOC", "", "20080", "700", "노스롭그루먼", "NORTHROP GRUMMAN", R.drawable.c_noc),
            Stock("NVDA", "", "20080", "700", "엔비디아", "NVIDIA", R.drawable.c_nvda),
            Stock("PEP", "", "20080", "700", "펩시", "PEPSI", R.drawable.c_pep),
            Stock("PFE", "", "20080", "700", "화이자", "PFIZER", R.drawable.c_pfe),
            Stock("PG", "", "P&20080", "700", "프록터 앤드 갬블", "P&G", R.drawable.c_pg),
            Stock("PM", "", "20080", "700", "필립 모리스 인터내셔널", "PHOLIP MORRIS", R.drawable.c_pm),
            Stock("QQQ", "", "20080", "700", "인베스코", "Invesco", R.drawable.c_qqq),
            Stock("RAD", "", "20080", "700", "라이트 에이드", "RITE AID", R.drawable.c_rad),
            Stock("RTX", "", "20080", "700", "레이시온", "Raytheon", R.drawable.c_rtx),
            Stock("SPY", "", "20080", "700", "스테이트 스트리트 코퍼레이션", "STATE STREET", R.drawable.c_spy),
            Stock("SYY", "", "20080", "700", "시스코", "Sysco", R.drawable.c_syy),
            Stock("T", "", "20080", "700", "AT&T", "AT&T", R.drawable.c_t),
            Stock("TGT", "", "20080", "700", "타깃", "TARGET", R.drawable.c_tgt),
            Stock("TRV", "", "20080", "700", "트래블러스", "TRAVELERS", R.drawable.c_trv),
            Stock("TSLA", "", "20080", "700", "테슬라", "TESLA", R.drawable.c_tsla),
            Stock("UNH", "", "20080", "700", "유나이티드헬스그룹", "UnitedHealth Group", R.drawable.c_unh),
            Stock("UPS", "", "20080", "700", "유피에스", "ups", R.drawable.c_ups),
            Stock("VLO", "", "20080", "700", "발레로 에너지", "VALERO", R.drawable.c_vlo),
            Stock("VZ", "", "20080", "700", "버라이즌", "verizon", R.drawable.c_vz),
            Stock("WBA", "", "20080", "700", "월그린스 부츠 얼라이언스", "Alliance Boots", R.drawable.c_wba),
            Stock("WFC", "", "20080", "700", "웰스 파고", "WELLS FARGO", R.drawable.c_wfc),
            Stock("WMT", "", "20080", "700", "월마트", "Walmart", R.drawable.c_wmt),
            Stock("XOM", "", "20080", "700", "엑슨모빌", "ExxonMobil", R.drawable.c_xom),
        )
        _stockList.value = stocks

        myStocks = arrayListOf(
            Stock("AAPL", "", "10000", "100", "애플", "Apple", R.drawable.c_aapl),
            Stock("ABC", "", "20000", "200", "아메리소스베르겐", "AmerisourceBergen", R.drawable.c_abc),
            Stock("ABT", "", "30000", "300", "애벗", "Abbot", R.drawable.c_abt),
            Stock("ADM", "", "40000", "400", "아처 대니얼스 미들랜드", "ADM", R.drawable.c_adm),
            Stock("AIG", "", "50000", "500", "아메리칸 인터내셔널 그룹", "AIG", R.drawable.c_alg),
            Stock("AMD", "", "60000", "600", "암드", "+AMD", R.drawable.c_amd),

        )
        _myStockList.value = myStocks


        stocks = arrayListOf(
            Stock("AAPL", "", "10000", "100", "애플", "Apple", R.drawable.c_aapl),
            Stock("ABC", "", "20000", "200", "아메리소스베르겐", "AmerisourceBergen", R.drawable.c_abc),
            Stock("ABT", "", "30000", "300", "애벗", "Abbot", R.drawable.c_abt),
            Stock("ADM", "", "40000", "400", "아처 대니얼스 미들랜드", "ADM", R.drawable.c_adm),
            Stock("AIG", "", "50000", "500", "아메리칸 인터내셔널 그룹", "AIG", R.drawable.c_alg),
            Stock("AMD", "", "60000", "600", "암드", "+AMD", R.drawable.c_amd),

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
                if (id.symbol_en == companyId) {
                    curStock = id
                    return id
                }
            }
        }
        return (Stock("콩쥐야", "X됐어", ".", ".", ".", "",1))
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