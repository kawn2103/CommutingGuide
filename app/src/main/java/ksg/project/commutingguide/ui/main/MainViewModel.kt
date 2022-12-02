package ksg.project.commutingguide.ui.main

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ksg.project.commutingguide.data.model.busStops.BusStops
import ksg.project.commutingguide.data.repository.BusStopsRepository
import ksg.project.commutingguide.utils.Constants
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val busStopsRepository: BusStopsRepository
): ViewModel(){
    var count = ObservableField(100)

    fun countPlus(){
        count.set(count.get()?.plus(1) ?: 0)
        Log.d("gwan2103","count >>>>> $count")
        searchBusStops()
    }

    private val _searchBusStopsResult :Flow<BusStops> = emptyFlow()

    fun searchBusStops() {
        viewModelScope.launch {
            val result = busStopsRepository.searchBusStops(Constants.API_KEY,1,10000,null,null)
                if (result.isSuccessful){
                    Log.d("gwan2103","result >>>> ${result.body()}")
                }
        }
    }
}