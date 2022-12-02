package ksg.project.commutingguide.data.model.busStops

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class BusStops(
    @Element(name = "body")
    val body: BusStopsBody,
    @Element(name="header")
    val header: BusStopsHeader
)