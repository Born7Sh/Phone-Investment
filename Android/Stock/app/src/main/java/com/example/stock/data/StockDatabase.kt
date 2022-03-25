//package com.example.stock.data
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//// https://developer.android.com/codelabs/android-room-with-a-view-kotlin?hl=ko#7 공식문서 참고
//
//// Room의 데이터베이스 클래스는 abstract이고 RoomDatabase.를 확장해야 합니다.
////클래스를 Room 데이터베이스가 되도록 @Database로 주석 처리하고 주석 매개변수를 사용하여 데이터베이스에 속한 항목을 선언하고 버전 번호를 설정합니다.
//// 각 항목은 데이터베이스에 만들어질 테이블에 상응합니다.
//// 데이터베이스 이전은 이 Codelab의 범위를 벗어나므로 exportSchema는 빌드 경고를 피하기 위해 false로 설정했습니다.
//// 실제 앱에서는 현재 스키마를 버전 제어 시스템으로 확인할 수 있도록 스키마를 내보내는 데 사용할 Room 디렉터리를 설정하는 것이 좋습니다.
//
//@Database(entities = [], version = 1, exportSchema = false)
//public abstract class StockDatabase : RoomDatabase() {
//    // 데이터베이스는 각 @Dao의 추상 'getter' 메서드를 통해 DAO를 노출합니다.
//    abstract fun stockDao(): StockDAO
//
//    companion object {
//        // 데이터베이스의 여러 인스턴스가 동시에 열리는 것을 막기 위해 StockDatabase,를 싱글톤으로 정의했습니다.
//        @Volatile
//        private var INSTANCE: StockDatabase? = null
//
//        fun getDatabase(context: Context): StockDatabase {
//            //getDatabase는 싱글톤을 반환합니다.
//            // 처음 액세스할 때 데이터베이스를 만들어 Room의 데이터베이스 빌더를 사용하여 StockDatabase 클래스의 애플리케이션 컨텍스트에서
//            // RoomDatabase 객체를 만들고 이름을 "stock_database"로 지정합니다.
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    StockDatabase::class.java,
//                    "stock_database"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//    }
//}