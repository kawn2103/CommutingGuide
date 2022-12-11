package ksg.project.commutingguide.utils

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.StateFlow
import ksg.project.commutingguide.data.model.busStops.BusStops
import ksg.project.commutingguide.ui.main.BusStopsAdapter

//xml에 선언된 함수명 선언 및 해당 함수에 해당하는 로직 선언
@BindingAdapter("show")
fun ConstraintLayout.bindShow(uiState:UiState<*>) {
    visibility = if (uiState is UiState.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter("toast")
fun View.bindToast(throwable: Throwable?) {
    throwable?.message?.let { errorMessage ->
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}

@BindingAdapter("adapter")
fun RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter("busStopItems")
fun RecyclerView.bindBusStopItems(uiState: UiState<BusStops>) {
    val boundAdapter = this.adapter
    if (boundAdapter is BusStopsAdapter) {
        val busStopsList = uiState.successOrNull()?.body?.items?.item
        boundAdapter.submitList(busStopsList)
    }
}