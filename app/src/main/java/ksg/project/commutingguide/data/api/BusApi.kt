package ksg.project.commutingguide.data.api

import ksg.project.commutingguide.data.model.busStops.BusStops
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BusApi {
    @GET("busStopList")
    suspend fun searchBusStops(
        @Query(value = "serviceKey",encoded = true) serviceKey: String,
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("bstopnm") bstopnm: String?,
        @Query("arsno") arsno: Int?,
    ): Response<BusStops>
}