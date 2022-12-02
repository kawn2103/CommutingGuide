package ksg.project.commutingguide.data.model.busStops

import com.tickaroo.tikxml.annotation.PropertyElement

data class BusStopsHeader(
    @PropertyElement(name="resultCode")
    val resultCode: Int,
    @PropertyElement(name="resultMsg")
    val resultMsg: String
)
