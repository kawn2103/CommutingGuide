package ksg.project.commutingguide.data.model.busStops

import com.tickaroo.tikxml.annotation.Xml
import com.tickaroo.tikxml.annotation.Element
@Xml(name = "items")
data class BusStopsItems(
    @Element(name="item")
    val item: List<BusStopsItem?>,
)
