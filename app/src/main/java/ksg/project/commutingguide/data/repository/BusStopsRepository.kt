package ksg.project.commutingguide.data.repository

import kotlinx.coroutines.flow.Flow
import ksg.project.commutingguide.data.model.busStops.BusStops
import ksg.project.commutingguide.utils.UiState

interface BusStopsRepository {

    //버스 정류장 조회
    //조회에 필요한 데이터를 전송받아 api 호출을 하고 해당 데이터를 Flow 형식으로 받아옴
    fun searchBusStops(
        serviceKey: String,
        pageNo: Int,
        numOfRows: Int,
        bstopnm: String?,
        arsno: Int?,
    ): Flow<UiState<BusStops>>
}