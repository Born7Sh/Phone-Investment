//package com.example.stock.data
//
//import androidx.room.*
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface StockDAO {
//    @Insert
//    suspend fun insert(stock: Stock)
//
//    @Update
//    suspend fun update(stock: Stock)
//
//    @Delete
//    suspend fun delete(stock: Stock)
//
//    //데이터가 변경되면 일반적으로 UI에 업데이트된 데이터를 표시하는 등 몇 가지 작업을 실행하는 것이 좋습니다.
//    // 즉, 데이터가 변경될 때 대응할 수 있도록 데이터를 관찰해야 합니다.
//    //
//    //데이터 변경사항을 관찰하려면 kotlinx-coroutines의 Flow를 사용합니다.
//    // 메서드 설명에서 Flow 유형의 반환 값을 사용하면 Room은 데이터베이스가 업데이트될 때 Flow를 업데이트하는 데 필요한 모든 코드를 생성합니다.
//
//    @Query("SELECT * FROM Stock") // 테이블의 모든 값을 가져와라
//    fun getAll(): Flow<List<Stock>>
//
//    @Query("DELETE FROM Stock WHERE name = :name") // 'name'에 해당하는 유저를 삭제해라
//    fun deleteUserByName(name: String)
//}
//
