package ksg.project.commutingguide.data.model.busStops
import com.tickaroo.tikxml.annotation.Xml
import com.tickaroo.tikxml.annotation.PropertyElement
@Xml(name = "item")
data class BusStopsItem(
    @PropertyElement(name = "bstopid")
    var bstopid: String?,
    @PropertyElement(name = "bstopnm")
    var bstopnm: String?,
    @PropertyElement(name="arsno")
    var arsno: String?,
    @PropertyElement(name = "gpsx")
    var gpsx: String?,
    @PropertyElement(name = "gpsy")
    var gpsy: String?,
    @PropertyElement(name="stoptype")
    var stoptype: String?,
)
