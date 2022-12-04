package ksg.project.commutingguide.data.repository

import kotlinx.coroutines.flow.Flow
import ksg.project.commutingguide.data.model.busStops.BusStops
import ksg.project.commutingguide.utils.UiState

interface BusStopsRepository {

    fun searchBusStops(
        serviceKey: String,
        pageNo: Int,
        numOfRows: Int,
        bstopnm: String?,
        arsno: Int?,
    ): Flow<UiState<BusStops>>
}