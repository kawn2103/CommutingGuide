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

//@Singleton => dagger2 주입시 싱글톤 선언에 사용됨
//@Inject => dagger2 에서 주입받는 데이터를 알아서 주입 받아 사용하게 됨
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
        // 데이터 통신 실시
        val response = api.searchBusStops(serviceKey, pageNo, numOfRows, bstopnm, arsno)
        // 데이터 통신 결과에 따른 후속 처리
        if (response.isSuccessful){ //통신 성공
            //운동 데이터가 비어있는지 확인 후 비어있다면 Exception 처리
            //운동 데이터가 있다면 데이터 변수에 저장
            val busStops: BusStops = response.body() ?: throw EmptyBodyException("[${response.code()}] - ${response.raw()}")
            //Log.d("gwan2103","busStops >>>>> $busStops")
            //통신 결과 데이터 반환
            emit(UiState.Success(busStops))
        }else{//통신 실패
            //네트워크 통신에 실패 했으면 Exception 처리
            throw NetworkFailureException("[${response.code()}] - ${response.raw()}")
        }
    }.catch {//에러 데이터 반환
        emit(UiState.Error(it))
    }
}