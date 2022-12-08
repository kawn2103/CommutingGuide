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
    //선언시 함수를 받아옴
    private val whenItemClicked: (BusStopsItem) -> Unit
): ListAdapter<BusStopsItem, BusStopsAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //레이아웃 인플레이터 선언
        val layoutInflater = LayoutInflater.from(parent.context)
        //리사이클러뷰에 사용될 바인딩 선언
        val binding = DataBindingUtil.inflate<ItemBusStopsBinding>(layoutInflater, R.layout.item_bus_stops, parent, false)
        return ViewHolder(binding).apply {
            //뷰홀더 생성 시 아이템 클릭시에 대한 리스너 같이 선언
            //바인드 된 뷰의 루트에 클릭 리스너 선언
            binding.root.setOnClickListener {
                //포지션 조회
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                //클릭 시 실행 할 함수
                whenItemClicked(
                    //반환할 데이터
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
            //데이터 바인딩에 선언된 뷰모델 및 클래스 연결
            binding.apply {
                busStop = item
                executePendingBindings()
            }
        }
    }

    //diffUtill
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<BusStopsItem>() {
            override fun areItemsTheSame(oldItem: BusStopsItem, newItem: BusStopsItem): Boolean =
                oldItem.bstopid == newItem.bstopid

            override fun areContentsTheSame(oldItem: BusStopsItem, newItem: BusStopsItem): Boolean =
                oldItem == newItem
        }
    }
}