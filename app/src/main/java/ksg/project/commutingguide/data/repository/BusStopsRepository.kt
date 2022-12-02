package ksg.project.commutingguide.data.repository

import kotlinx.coroutines.flow.Flow
import ksg.project.commutingguide.data.model.busStops.BusStops
import retrofit2.Response

interface BusStopsRepository {

    suspend fun searchBusStops(
        serviceKey: String,
        pageNo: Int,
        numOfRows: Int,
        bstopnm: String?,
        arsno: Int?,
    ): Response<BusStops>
}