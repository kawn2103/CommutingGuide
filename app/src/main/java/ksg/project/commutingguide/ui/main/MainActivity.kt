package ksg.project.commutingguide.ui.main

import android.app.ActivityOptions
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.LocationOverlay
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import ksg.project.commutingguide.R
import ksg.project.commutingguide.databinding.ActivityMainBinding
import ksg.project.commutingguide.ui.base.BaseActivity
import ksg.project.commutingguide.utils.collectLatestStateFlow
import ksg.project.commutingguide.utils.successOrNull
import okhttp3.internal.notifyAll

//액티비티 di에 적용
//AndroidEntryPoint => 화면 선언에 사용
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main), OnMapReadyCallback {
    //viewModel 선언
    private val viewModel: MainViewModel by viewModels()
    //리사이클러 뷰 어댑터 생성
    private val busStopsAdapter: BusStopsAdapter by lazy {
        //리사이클러뷰 어댑터 생성시 함수를 받아와야해서 함수와 함께 선언
        BusStopsAdapter { busStopsItem ->
            Log.d("gwan2103","busStopsItem >>>> $busStopsItem")
        }
    }
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private var markerList = mutableListOf<Marker>()
    private var selfLat: Double? = null
    private var selfLon: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //데이터 바인딩에 선언된 뷰모델 및 클래스 연결
        bind {
            vm = viewModel
            adapter = busStopsAdapter
        }
        naverMapSetting()
        collectDataInit()
    }


    private fun naverMapSetting(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.mapFragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.mapFragment, it).commit()
            }

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        val uiSettings = naverMap.uiSettings
        uiSettings.isZoomControlEnabled = true
        uiSettings.isLocationButtonEnabled = true
        uiSettings.logoGravity = Gravity.END or Gravity.TOP
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        locationSource.isCompassEnabled = true
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Face
        val locationOverlay: LocationOverlay = naverMap.locationOverlay
        locationOverlay.isVisible = true

        naverMap.addOnLocationChangeListener { location ->
            selfLat = location.latitude
            selfLon = location.longitude

            locationOverlay.position = LatLng(selfLat!!, selfLon!!)
            locationOverlay.bearing = locationOverlay.bearing
            Log.d("gwan2103", "selfLat>>>>> $selfLat //// selfLon>>>>> $selfLon")
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions,
                grantResults
            )) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun collectDataInit(){
        //flow로 받아온 데이터 콜렉트 및 데이터 처리
        //util로 선언해서 사용함
        collectLatestStateFlow(viewModel.busStops) {
            //Log.d("gwan2103","busStops >>>>>> $it")
            val busStopsList = it.successOrNull()?.body?.items?.item
            busStopsAdapter.submitList(busStopsList)
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}