package ksg.project.commutingguide.data.model.busStops
import com.tickaroo.tikxml.annotation.Xml
import com.tickaroo.tikxml.annotation.PropertyElement

@Xml(name = "header")
data class BusStopsHeader(
    @PropertyElement(name="resultCode")
    val resultCode: Int,
    @PropertyElement(name="resultMsg")
    val resultMsg: String,
)
