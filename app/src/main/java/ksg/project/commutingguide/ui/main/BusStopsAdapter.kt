package ksg.project.commutingguide.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ksg.project.commutingguide.R
import ksg.project.commutingguide.data.model.busStops.BusStopsItem
import ksg.project.commutingguide.databinding.ItemBusStopsBinding


class BusStopsAdapter(
    private val whenItemClicked: (BusStopsItem) -> Unit
): ListAdapter<BusStopsItem, BusStopsAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemBusStopsBinding>(layoutInflater, R.layout.item_bus_stops, parent, false)
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                whenItemClicked(
                    getItem(position)
                )
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit =
        holder.bind(getItem(position))

    inner class ViewHolder(private val binding: ItemBusStopsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BusStopsItem) {
            binding.apply {
                busStop = item
                executePendingBindings()
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<BusStopsItem>() {
            override fun areItemsTheSame(oldItem: BusStopsItem, newItem: BusStopsItem): Boolean =
                oldItem.bstopid == newItem.bstopid

            override fun areContentsTheSame(oldItem: BusStopsItem, newItem: BusStopsItem): Boolean =
                oldItem == newItem
        }
    }
}