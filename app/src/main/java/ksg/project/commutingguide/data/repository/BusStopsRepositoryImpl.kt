package ksg.project.commutingguide.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import ksg.project.commutingguide.data.api.BusApi
import ksg.project.commutingguide.data.model.busStops.BusStops
import ksg.project.commutingguide.utils.EmptyBodyException
import ksg.project.commutingguide.utils.NetworkFailureException
import ksg.project.commutingguide.utils.UiState
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusStopsRepositoryImpl @Inject constructor(
    private val api: BusApi
) :BusStopsRepository {
    override fun searchBusStops(
        serviceKey: String,
        pageNo: Int,
        numOfRows: Int,
        bstopnm: String?,
        arsno: Int?
    ): Flow<UiState<BusStops>> = flow<UiState<BusStops>>{
        val response = api.searchBusStops(serviceKey, pageNo, numOfRows, bstopnm, arsno)

        if (response.isSuccessful){
            val busStops: BusStops = response.body() ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
            //Log.d("gwan2103","busStops >>>>> $busStops")
            emit(UiState.Success(busStops))
        }else{
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }

    }.catch { emit(UiState.Error(it)) }
}