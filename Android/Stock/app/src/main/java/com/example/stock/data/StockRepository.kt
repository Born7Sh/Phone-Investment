//package com.example.stock.data
//
//import androidx.annotation.WorkerThread
//import java.util.concurrent.Flow
//
//class StockRepository (private val stockDao: StockDAO) {
//
//    //DAO는 전체 데이터베이스가 아닌 저장소 생성자에 전달됩니다. DAO에 데이터베이스의 모든 읽기/쓰기 메서드가 포함되어 있으므로 DAO 액세스만 필요하기 때문입니다.
//    // 전체 데이터베이스를 저장소에 노출할 필요가 없습니다.
//
//    // 단어 목록은 공개 속성입니다. 이 목록은 Room에서 단어의 Flow 목록을 가져와서 초기화됩니다.
//    // '데이터베이스 변경사항 관찰' 단계에서 Flow를 반환하도록 getAlphabetizedWords 메서드를 정의한 방식에 따라 이렇게 할 수 있습니다.
//    // Room은 별도의 스레드에서 모든 쿼리를 실행합니다.
//
//
//    val allStocks: kotlinx.coroutines.flow.Flow<List<Stock>> = stockDao.getAll()
//
//    // suspend 수정자는 코루틴이나 다른 정지 함수에서 이를 호출해야 한다고 컴파일러에 알립니다.
//    // Room은 기본 스레드 밖에서 정지 쿼리를 실행합니다
//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun insert(stock: Stock) {
//        stockDao.insert(stock)
//    }
//}