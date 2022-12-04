package ksg.project.commutingguide.utils

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import kotlinx.coroutines.flow.StateFlow
import ksg.project.commutingguide.data.model.busStops.BusStops

@BindingAdapter("show")
fun ProgressBar.bindShow(uiState:UiState<*>) {
    visibility = if (uiState is UiState.Loading) View.VISIBLE else View.GONE
}
