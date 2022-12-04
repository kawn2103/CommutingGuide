package ksg.project.commutingguide.data.model.busStops
import com.tickaroo.tikxml.annotation.Xml
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
@Xml(name = "body")
data class BusStopsBody(
    @Element(name="items")
    val items: BusStopsItems,
    @PropertyElement(name="numOfRows")
    val numOfRows: Int,
    @PropertyElement(name="pageNo")
    val pageNo: Int,
    @PropertyElement(name="totalCount")
    val totalCount: Int,
)
