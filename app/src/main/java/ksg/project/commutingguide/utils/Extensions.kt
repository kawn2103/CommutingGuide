package ksg.project.commutingguide.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ksg.project.commutingguide.ui.main.MainActivity
import ksg.project.commutingguide.ui.searchBusStops.SearchFragment

//flow 데이터 처리
fun <T> MainActivity.collectLatestStateFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {//lifecycle에 연동
        repeatOnLifecycle(Lifecycle.State.STARTED) {//
            flow.collectLatest(collect)
        }
    }
}

fun <T> SearchFragment.collectLatestStateFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {//lifecycle에 연동
        repeatOnLifecycle(Lifecycle.State.STARTED) {//
            flow.collectLatest(collect)
        }
    }
}