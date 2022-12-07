package ksg.project.commutingguide.utils

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.StateFlow
import ksg.project.commutingguide.data.model.busStops.BusStops
import ksg.project.commutingguide.ui.main.BusStopsAdapter

@BindingAdapter("show")
fun ProgressBar.bindShow(uiState:UiState<*>) {
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

@BindingAdapter("championItems")
fun RecyclerView.bindChampionItems(uiState: UiState<BusStops>) {
    val boundAdapter = this.adapter
    if (boundAdapter is BusStopsAdapter) {
        val busStopsList = uiState.successOrNull()?.body?.items?.item
        boundAdapter.submitList(busStopsList)
    }
}