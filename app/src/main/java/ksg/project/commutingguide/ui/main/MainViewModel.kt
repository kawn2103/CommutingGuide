package ksg.project.commutingguide.ui.main

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ksg.project.commutingguide.data.model.busStops.BusStops
import ksg.project.commutingguide.data.repository.BusStopsRepository
import ksg.project.commutingguide.utils.Constants
import ksg.project.commutingguide.utils.UiState
import ksg.project.commutingguide.utils.successOrNull
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val busStopsRepository: BusStopsRepository
): ViewModel(){
    /*//데이터 옵저빙을 위한 옵저버블 데이터 선언
    var count = ObservableField(0)
    //내부에서 데이터를 받아오기 위한 뮤터블플로우 데이터 선언
    private val _busStops: MutableStateFlow<UiState<BusStops>> = MutableStateFlow(UiState.None)
    //view에 데이터를 반영하기 위한 데이터 선언
    val busStops: StateFlow<UiState<BusStops>> = _busStops.asStateFlow()

    fun countPlus(){
        count.set(count.get()?.plus(1) ?: 0)
        searchBusStops()
    }

    private fun searchBusStops() {
        //레포지토리에서 데이터 조회를 위해서 스코프 선언
        viewModelScope.launch {
            //데이터 통신 시 시작 상태 변경
            _busStops.value = UiState.Loading
            //데이터 통신 및 결과 데이터 수집
            busStopsRepository.searchBusStops(Constants.API_KEY,count.get() ?: 1,10000,null,null)
                .collect {
                    _busStops.value = it
                }
        }
    }*/

    val busStops: StateFlow<UiState<BusStops>> =
        busStopsRepository.searchBusStops(Constants.API_KEY,1,10000,null,null)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = UiState.Loading
            )
}