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
import okhttp3.internal.notifyAll

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()
    private val busStopsAdapter: BusStopsAdapter by lazy {
        BusStopsAdapter { busStopsItem ->
            Log.d("gwan2103","busStopsItem >>>> $busStopsItem")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind {
            vm = viewModel
            adapter = busStopsAdapter
        }

        /*collectLatestStateFlow(viewModel.busStops) {
            Log.d("gwan2103","busStops >>>>>> $it")
        }*/

    }
}