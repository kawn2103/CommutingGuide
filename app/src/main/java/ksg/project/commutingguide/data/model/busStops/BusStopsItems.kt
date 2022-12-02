package ksg.project.commutingguide.data.model.busStops

import android.content.ClipData
import com.tickaroo.tikxml.annotation.Element

data class BusStopsItems(
    @Element(name="item")
    val item: List<ClipData.Item>
)
