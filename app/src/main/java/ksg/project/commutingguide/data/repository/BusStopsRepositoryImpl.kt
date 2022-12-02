package ksg.project.commutingguide.data.repository

import kotlinx.coroutines.flow.Flow
import ksg.project.commutingguide.data.api.BusApi
import ksg.project.commutingguide.data.model.busStops.BusStops
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusStopsRepositoryImpl @Inject constructor(
    private val api: BusApi
) :BusStopsRepository {
    override suspend fun searchBusStops(
        serviceKey: String,
        pageNo: Int,
        numOfRows: Int,
        bstopnm: String?,
        arsno: Int?
    ): Response<BusStops> {
        return api.searchBusStops(serviceKey, pageNo, numOfRows, bstopnm, arsno)
    }
}