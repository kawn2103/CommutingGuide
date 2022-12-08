package ksg.project.commutingguide.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

//데이터바인딩을 받아서 사용할 수 있는 베이스 뷰생성
abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
    ) : AppCompatActivity() {
    //바인딩 생성 및 화면 연결
    protected val binding: T by lazy(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.setContentView(this, layoutId)
    }

    init {
        addOnContextAvailableListener {
            binding.notifyChange()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //엑티비티와 라이프 사이클 동기화
        binding.lifecycleOwner = this
    }

    override fun onDestroy() {
        //바인딩 해제
        binding.unbind()
        super.onDestroy()
    }

    //바인딩 연결
    protected inline fun bind(block: T.() -> Unit) {
        binding.apply(block)
    }
}