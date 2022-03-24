package com.example.stock.model

import androidx.lifecycle.*
import com.example.stock.data.Stock
import com.example.stock.data.StockRepository
import kotlinx.coroutines.launch

// StockRepository를 매개변수로 가져오고 ViewModel을 확장하는 StockViewModel이라는 클래스를 만들었습니다.
// Repositary는 ViewModel에 필요한 유일한 종속 항목입니다.
// 다른 클래스가 필요했다면 생성자에도 전달되었을 것입니다.
// 주식 목록을 캐시하는 공개 LiveData 멤버 변수를 추가했습니다.
// StockRepository의 allStocks 흐름을 사용하여 LiveData를 초기화했습니다. 그런 다음 asLiveData().를 호출하여 Flow를 LiveData로 변환했습니다.
// 저장소의 insert() 메서드를 호출하는 래퍼 insert() 메서드를 만들었습니다. 이렇게 하면 insert() 구현이 UI에서 캡슐화됩니다.
// 새 코루틴을 실행하고 정지 함수인 저장소의 insert를 호출합니다.

class StockViewModel(private val repository: StockRepository) : ViewModel() {

    val allStocks: LiveData<List<Stock>> = repository.allStocks.asLiveData()

    // 앞서 언급한 바와 같이 ViewModel에는 viewModelScope이라는 수명 주기 기반의 코루틴 범위가 있으며 여기서 사용합니다.
// ViewModel을 만들고 StockViewModel을 만드는 데 필요한 종속 항목(WordRepository)을 매개변수로 가져오는 ViewModelProvider.Factory를 구현했습니다.

    fun insert(stock: Stock) = viewModelScope.launch {
        repository.insert(stock)
    }

    // viewModels와 ViewModelProvider.Factory를 사용하여 프레임워크에서 ViewModel의 수명 주기를 처리합니다.
    // 구성 변경에도 유지되고 Activity가 다시 생성되더라도 항상 WordViewModel 클래스의 올바른 인스턴스를 가져오게 됩니다
    class WordViewModelFactory(private val repository: StockRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return StockViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}

