package ksg.project.commutingguide.data.model.busStops

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

//xml 데이터를 파싱하기 위한 준비
//Xml => xml 상의 데이터 name을 정의할 때 사용
//Element => 데이터 클래스를 파싱할 때 사용
//PropertyElement => 데이터 자체를 파싱할 때 사용
@Xml(name = "response")
data class BusStops(
    @Element(name="header")
    val header: BusStopsHeader,
    @Element(name = "body")
    val body: BusStopsBody,
)