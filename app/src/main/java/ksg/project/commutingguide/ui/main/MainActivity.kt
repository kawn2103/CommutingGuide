package ksg.project.commutingguide.ui.main

import android.app.ActivityOptions
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
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
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    //viewModel 선언
    private val viewModel: MainViewModel by viewModels()
    //리사이클러 뷰 어댑터 생성
    private val busStopsAdapter: BusStopsAdapter by lazy {
        //리사이클러뷰 어댑터 생성시 함수를 받아와야해서 함수와 함께 선언
        BusStopsAdapter { busStopsItem ->
            Log.d("gwan2103","busStopsItem >>>> $busStopsItem")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //데이터 바인딩에 선언된 뷰모델 및 클래스 연결
        bind {
            vm = viewModel
            adapter = busStopsAdapter
        }

        //flow로 받아온 데이터 콜렉트 및 데이터 처리
        //util로 선언해서 사용함
        collectLatestStateFlow(viewModel.busStops) {
            //Log.d("gwan2103","busStops >>>>>> $it")
            val busStopsList = it.successOrNull()?.body?.items?.item
            busStopsAdapter.submitList(busStopsList)
        }

    }
}