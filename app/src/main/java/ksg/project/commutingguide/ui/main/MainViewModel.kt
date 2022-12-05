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
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val busStopsRepository: BusStopsRepository
): ViewModel(){
    var count = ObservableField(0)
    val _busStops: MutableStateFlow<UiState<BusStops>> = MutableStateFlow(UiState.Loading)
    val busStops: StateFlow<UiState<BusStops>> get() = _busStops.asStateFlow()

    fun countPlus(){
        count.set(count.get()?.plus(1) ?: 0)
        refreshData()
    }

    fun refreshData() = viewModelScope.launch {
        val result = busStopsRepository.searchBusStops(Constants.API_KEY,count.get() ?: 1,10,null,null)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = UiState.Loading
            )
        delay(10000)
        Log.d("gwan2103","result >>>> $result")
        Log.d("gwan2103","result.value >>>> ${result.value}")
        //_busStops.emit(result.value)
    }

    /*fun callBusStops(): StateFlow<UiState<BusStops>> {
        return busStopsRepository.searchBusStops(
            Constants.API_KEY,
            count.get() ?: 1,
            10,
            null,
            null
        )
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = UiState.Loading
            )
    }*/

    /*val busStops: StateFlow<UiState<BusStops>> =
        busStopsRepository.searchBusStops(Constants.API_KEY,count.get() ?: 1,10,null,null)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = UiState.Loading
            )*/
}