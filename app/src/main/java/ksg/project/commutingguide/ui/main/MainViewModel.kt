package ksg.project.commutingguide.ui.main

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
): ViewModel(){
    var count = ObservableField(100)

    fun countPlus(){
        count.set(count.get()?.plus(1) ?: 0)
        Log.d("gwan2103","count >>>>> $count")
    }
}