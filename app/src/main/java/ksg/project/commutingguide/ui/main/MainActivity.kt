package ksg.project.commutingguide.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ksg.project.commutingguide.R
import ksg.project.commutingguide.databinding.ActivityMainBinding
import ksg.project.commutingguide.ui.base.BaseActivity
import ksg.project.commutingguide.utils.collectLatestStateFlow

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind {
            vm = viewModel
        }

        /*collectLatestStateFlow(viewModel.busStops) {
            Log.d("gwan2103","busStops >>>>>> $it")
        }*/

    }
}