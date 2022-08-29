package com.example.stock.global

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.stock.BuildConfig
import com.example.stock.R
import com.example.stock.data.AppDataBase
import com.example.stock.util.AndroidKeyStoreUtil
import com.example.stock.data.model.Auth
import com.example.stock.util.SecureSharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.Executors

class GlobalApplication : Application() {
    companion object {
        lateinit var sharedPrefs: SharedPreferences

        // Retrofit2
        lateinit var baseService: Retrofit
            private set

        // 로그인 했냐?
        var haveLogin: Boolean = true
            private set

        // 키 반환용 변수
        lateinit var auth: Auth
            private set

        lateinit var key: String

        // 유저 돈
//        lateinit var money: Int = 0


        // ROOM 설정용 데이터베이스 변수 2개
        lateinit var appInstance: GlobalApplication
            private set

        lateinit var appDataBaseInstance: AppDataBase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        // room 초기화용도
        appInstance = this


        // 콜백함수로 데이터베이스 초기 데이터 집어넣기
        var dbCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                Executors.newSingleThreadScheduledExecutor()
                    .execute(Runnable {
                        db.execSQL("insert into Stock values (\"AAPL\", \"\", 0, 0, \"애플\", \"Apple\", 'c_aapl', 0);")
                        db.execSQL("insert into Stock values (\"ABC\", \"\", 0, 0, \"아메리소스베르겐\", \"AmerisourceBergen\", 'c_abc', 1);")
                        db.execSQL("insert into Stock values (\"ABT\", \"\", 0, 0, \"애벗\", \"Abbot\", 'c_abt', 1);")
                        db.execSQL("insert into Stock values (\"ADM\", \"\", 0, 0, \"아처 대니얼스 미들랜드\", \"ADM\", 'c_adm', 1);")
                        db.execSQL("insert into Stock values (\"AIG\", \"\", 0, 0, \"아메리칸 인터내셔널 그룹\", \"AIG\", 'c_alg', 1);")
                        db.execSQL("insert into Stock values (\"AMD\", \"\", 0, 600, \"암드\", \"+AMD\", 'c_amd', 0);")
                        db.execSQL("insert into Stock values (\"AMZN\", \"\", 0, 0, \"아마존\", \"Amazon\", 'c_amzn', 0);")
                        db.execSQL("insert into Stock values (\"ANTM\", \"\", 0, 0, \"앤썸\", \"Anthem\", 'c_antm', 0);")
                        db.execSQL("insert into Stock values (\"AXP\", \"\", \"90000\", \"800\", \"아메리칸 익스프레스\", \"American Express\", 'c_axp', 0);")
                        db.execSQL("insert into Stock values (\"BA\", \"\", \"10000\", \"200\", \"보잉\", \"BOEING\",'c_ba', 0);")
                        db.execSQL("insert into Stock values (\"BAC\", \"\", \"20000\", \"230\", \"뱅크 오브 아메리카\", \"Bank of America\", 'c_bac', 0);")
                        db.execSQL("insert into Stock values (\"BBY\", \"\", \"20030\", \"350\", \"베스트 바이\", \"BEST BUY\", 'c_bby', 0);")
                        db.execSQL("insert into Stock values (\"C\", \"\", \"20040\", \"230\", \"씨티그룹\", \"CITI\", 'c_c', 0);")
                        db.execSQL("insert into Stock values (\"CAH\", \"\", \"20050\", \"500\", \"카디널헬스\", \"CardinalHealth\",'c_cah', 0);")
                        db.execSQL("insert into Stock values (\"CAT\", \"\", \"20060\", \"650\", \"캐터필러\", \"CAT\", 'c_cat', 0);")
                        db.execSQL("insert into Stock values (\"CHS\", \"\", \"20070\", \"700\", \"chico's\", \"chico's\", 'c_chs', 0);")
                        db.execSQL("insert into Stock values (\"CMCSA\", \"\", \"20080\", \"800\", \"컴캐스트\", \"COMCAST\",'c_cmcsa', 0);")
                        db.execSQL("insert into Stock values (\"COP\", \"\", \"20080\", \"700\", \"코노코필립스 \", \"ConocoPhillips\",'c_cop', 0);")
                        db.execSQL("insert into Stock values (\"COST\", \"\", \"20080\", \"700\", \"코스트코\", \"Costoco\", 'c_cost', 0);")
                        db.execSQL("insert into Stock values (\"CPNG\", \"\", \"20080\", \"700\", \"쿠팡\", \"coupang\", 'c_cpng', 0);")
                        db.execSQL("insert into Stock values (\"CSCO\", \"\", \"20080\", \"700\", \"시스코\", \"cisco\", 'c_csco', 0);")
                        db.execSQL("insert into Stock values (\"CVS\", \"\", \"20080\", \"700\", \"CVS 케어마크\", \"CVSHealth\", 'c_cvs', 0);")
                        db.execSQL("insert into Stock values (\"CVX\", \"\", \"20080\", \"700\", \"셰브론\", \"Chevron\", 'c_cvx', 0);")
                        db.execSQL("insert into Stock values (\"DAL\", \"\", \"20080\", \"700\", \"델타\", \"DELTA\", 'c_dal', 0);")
                        db.execSQL("insert into Stock values (\"DD\", \"\", \"20080\", \"700\", \"듀폰\", \"DUPONT\", 'c_dd', 0);")
                        db.execSQL("insert into Stock values (\"DELL\", \"\", \"20080\", \"700\", \"델\", \"DELL\", 'c_dell', 0);")
                        db.execSQL("insert into Stock values (\"DIS\", \"\", \"20080\", \"700\", \"디지니\", \"Walt Disney\", 'c_dis', 0);")
                        db.execSQL("insert into Stock values (\"F\", \"\", \"20080\", \"700\", \"포드\", \"Fored\", 'c_f', 0);")
                        db.execSQL("insert into Stock values (\"FB\", \"\", \"20080\", \"700\", \"메타\", \"Meta\", 'c_fb', 0);")
                        db.execSQL("insert into Stock values (\"FDX\", \"\", \"20080\", \"700\", \"페덱스\", \"FedEx\",'c_fdx', 0);")
                        db.execSQL("insert into Stock values (\"GD\", \"\", \"20080\", \"700\", \"제너럴 다이내믹스\", \"GENERAL DYNAMICS\",'c_gd', 0);")
                        db.execSQL("insert into Stock values (\"GE\", \"\", \"20080\", \"700\", \"제너럴 일렉트릭\", \"General Electric\", 'c_ge', 0);")
                        db.execSQL("insert into Stock values (\"GM\", \"\", \"20080\", \"700\", \"쉐보레\", \"CHEVROLET\",'c_gm', 0);")
                        db.execSQL("insert into Stock values (\"GOOG\", \"\", \"20080\", \"700\", \"구글\", \"Google\",'c_goog', 0);")
                        db.execSQL("insert into Stock values (\"GS\", \"\", \"20080\", \"700\", \"골드만삭스\", \"GoldmanSachs\",'c_gs', 0);")
                        db.execSQL("insert into Stock values (\"HCA\", \"\", \"20080\", \"700\", \"미국 병원 법인\", \"HCA\",'c_hca', 0);")
                        db.execSQL("insert into Stock values (\"HD\", \"\", \"20080\", \"700\", \"홈디포\", \"THE HOME DEPOT\", 'c_hd', 0);")
                        db.execSQL("insert into Stock values (\"HES\", \"\", \"20080\", \"700\", \"헤스 코퍼레이션\", \"HESS\",'c_hes', 0);")
                        db.execSQL("insert into Stock values (\"HIG\", \"\", \"20080\", \"700\", \"하트퍼드\", \"THE HARTFORD\",'c_hig', 0);")
                        db.execSQL("insert into Stock values (\"HON\", \"\", \"20080\", \"700\", \"허니웰\", \"Honeywell\",'c_hon', 0);")
                        db.execSQL("insert into Stock values (\"HPQ\", \"\", \"20080\", \"700\", \"휴렛 팩커드\", \"HP\", 'c_hpq', 0);")
                        db.execSQL("insert into Stock values (\"IBM\", \"\", \"20080\", \"700\", \"IBM\", \"IBM\", 'c_ibm', 0);")
                        db.execSQL("insert into Stock values (\"INTC\", \"\", \"20080\", \"700\", \"인텔\", \"intel\", 'c_intc', 0);")
                        db.execSQL("insert into Stock values (\"JCL\", \"\", \"20080\", \"700\", \"존슨컨트롤즈\", \"Johnson Controls\", 'c_jcl', 0);")
                        db.execSQL("insert into Stock values (\"JNJ\", \"\", \"20080\", \"700\", \"존슨앤드존슨\", \"Johnson & Johnson\", 'c_jnj', 0);")
                        db.execSQL("insert into Stock values (\"JPM\", \"\", \"20080\", \"700\", \"JP모간\", \"J.P.Morgan\",'c_jpm', 0);")
                        db.execSQL("insert into Stock values (\"KO\", \"\", \"20080\", \"700\", \"코카콜라\", \"CocaCola\",'c_ko', 0);")
                        db.execSQL("insert into Stock values (\"KR\", \"\", \"20080\", \"700\", \"크로거\", \"Kroger\",'c_kr', 0);")
                        db.execSQL("insert into Stock values (\"MCK\", \"\", \"20080\", \"700\", \"MCKESSON\", \"MCKESSON\", 'c_mck', 0);")
                        db.execSQL("insert into Stock values (\"LMT\", \"\", \"20080\", \"700\", \"록히드 마틴\", \"LOCKHED MARTIN\",'c_lmt', 0);")
                        db.execSQL("insert into Stock values (\"MET\", \"\", \"20080\", \"700\", \"메트라이프\", \"MetLife\",'c_met', 0);")
                        db.execSQL("insert into Stock values (\"MRK\", \"\", \"20080\", \"700\", \"머크 그룹\", \"MERCK\", 'c_mrk', 0);")
                        db.execSQL("insert into Stock values (\"MRO\", \"\", \"20080\", \"700\", \"마라톤 오일\", \"Marathon OIL\", 'c_mro', 0);")
                        db.execSQL("insert into Stock values (\"MS\", \"\", \"20080\", \"700\", \"모건 스탠리\", \"Morgan Stanley\", 'c_ms', 0);")
                        db.execSQL("insert into Stock values (\"MSFT\", \"\", \"20080\", \"700\", \"마이크로소프트\", \"Microsoft\", 'c_msft', 0);")
                        db.execSQL("insert into Stock values (\"NOC\", \"\", \"20080\", \"700\", \"노스롭그루먼\", \"NORTHROP GRUMMAN\", 'c_noc', 0);")
                        db.execSQL("insert into Stock values (\"NVDA\", \"\", \"20080\", \"700\", \"엔비디아\", \"NVIDIA\",'c_nvda', 0);")
                        db.execSQL("insert into Stock values (\"PEP\", \"\", \"20080\", \"700\", \"펩시\", \"PEPSI\", 'c_pep', 0);")
                        db.execSQL("insert into Stock values (\"PFE\", \"\", \"20080\", \"700\", \"화이자\", \"PFIZER\",'c_pfe', 0);")
                        db.execSQL("insert into Stock values (\"PG\", \"\", \"P&20080\", \"700\", \"프록터 앤드 갬블\", \"P&G\", 'c_pg', 0);")
                        db.execSQL("insert into Stock values (\"PM\", \"\", \"20080\", \"700\", \"필립 모리스 인터내셔널\", \"PHOLIP MORRIS\", 'c_pm', 0);")
                        db.execSQL("insert into Stock values (\"QQQ\", \"\", \"20080\", \"700\", \"인베스코\", \"Invesco\",'c_qqq', 0);")
                        db.execSQL("insert into Stock values (\"RAD\", \"\", \"20080\", \"700\", \"라이트 에이드\", \"RITE AID\", 'c_rad', 0);")
                        db.execSQL("insert into Stock values (\"RTX\", \"\", \"20080\", \"700\", \"레이시온\", \"Raytheon\", 'c_rtx', 0);")
                        db.execSQL("insert into Stock values (\"SPY\", \"\", \"20080\", \"700\", \"스테이트 스트리트 코퍼레이션\", \"STATE STREET\", 'c_spy', 0);")
                        db.execSQL("insert into Stock values (\"SYY\", \"\", \"20080\", \"700\", \"시스코\", \"Sysco\", 'c_syy', 0);")
                        db.execSQL("insert into Stock values (\"T\", \"\", \"20080\", \"700\", \"AT&T\", \"AT&T\", 'c_t', 0);")
                        db.execSQL("insert into Stock values (\"TGT\", \"\", \"20080\", \"700\", \"타깃\", \"TARGET\",'c_tgt', 0);")
                        db.execSQL("insert into Stock values (\"TRV\", \"\", \"20080\", \"700\", \"트래블러스\", \"TRAVELERS\",'c_trv', 0);")
                        db.execSQL("insert into Stock values (\"TSLA\", \"\", \"20080\", \"700\", \"테슬라\", \"TESLA\", 'c_tsla', 0);")
                        db.execSQL("insert into Stock values (\"UNH\", \"\", \"20080\", \"700\", \"유나이티드헬스그룹\", \"UnitedHealth Group\", 'c_unh', 0);")
                        db.execSQL("insert into Stock values (\"UPS\", \"\", \"20080\", \"700\", \"유피에스\", \"ups\", 'c_ups', 0);")
                        db.execSQL("insert into Stock values (\"VLO\", \"\", \"20080\", \"700\", \"발레로 에너지\", \"VALERO\",'c_vlo', 0);")
                        db.execSQL("insert into Stock values (\"VZ\", \"\", \"20080\", \"700\", \"버라이즌\", \"verizon\", 'c_vz', 0);")
                        db.execSQL("insert into Stock values (\"WBA\", \"\", \"20080\", \"700\", \"월그린스 부츠 얼라이언스\", \"Alliance Boots\",'c_wba', 0);")
                        db.execSQL("insert into Stock values (\"WFC\", \"\", \"20080\", \"700\", \"웰스 파고\", \"WELLS FARGO\", 'c_wfc', 0);")
                        db.execSQL("insert into Stock values (\"WMT\", \"\", \"20080\", \"700\", \"월마트\", \"Walmart\", 'c_wmt', 0);")
                        db.execSQL("insert into Stock values (\"XOM\", \"\", \"20080\", \"700\", \"엑슨모빌\", \"ExxonMobil\", 'c_xom', 0);")


                    })
            }
        }
        // room 초기화용도
        appDataBaseInstance = Room.databaseBuilder(
            appInstance.applicationContext,
            AppDataBase::class.java, "stock.db"
        )
            .fallbackToDestructiveMigration() // DB version 달라졌을 경우 데이터베이스 초기화
            .allowMainThreadQueries() // 메인 스레드에서 접근 허용
            .addCallback(dbCallback)
            .build()


        // 여기 밑에는 Retrofit 용도

//        prefs = MySharedPreferences(applicationContext)
//        https://hyperconnect.github.io/2018/06/03/android-secure-sharedpref-howto.html

        AndroidKeyStoreUtil.init(this)
        sharedPrefs = getSharedPreferences("loginData", MODE_PRIVATE)
        val secureSharedPreferences = SecureSharedPreferences.wrap(sharedPrefs)

        secureSharedPreferences.put("id", "homin")
        secureSharedPreferences.put("pass", "1234")

        val sharedPreferenceId = secureSharedPreferences.get("id", "NULL")
        val sharedPreferencePw = secureSharedPreferences.get("pass", "NULL")
//        key = secureSharedPreferences.get("key", "NULL")

//        Log.v("items", "id = " + auth.username)
//        Log.v("items", "pass = " + auth.username)
//        Log.v("items", "key = " + c)

        if (sharedPreferenceId == "NULL" && sharedPreferencePw == "NULL") {
            haveLogin = false
        } else {
            auth = Auth(sharedPreferenceId, sharedPreferencePw)
        }

        baseService = initRetrofitBuilder()
    }

    private fun initRetrofitBuilder(): Retrofit {
        // 기본 주소
//        val BASE_URL = "http://222.112.18.141:8080/"
        val BASE_URL = "https://9de12e98-1be1-47bd-90c2-c2a6f1b00cd3.mock.pstmn.io/"


        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()


        // 로그용도
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        val client = OkHttpClient().newBuilder().addNetworkInterceptor(interceptor).build()

        //리턴하는 레트로핏 빌더 반환
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


    }

}