package ksg.project.commutingguide.utils

//UI에 반영하는 상태 클래스
sealed class UiState<out T> {
    object None: UiState<Nothing>()  //데이터의 로딩 상태
    object Loading: UiState<Nothing>()  //데이터의 로딩 상태
    data class Success<T>(val data: T): UiState<T>()
    data class Error(val error: Throwable?): UiState<Nothing>()
}
//네트워크 통신에 성공하고 데이터를 받아오는 상태
fun <T> UiState<T>.successOrNull(): T? = if (this is UiState.Success<T>) {
    //성공시 데이터 반환
    data
} else {
    //빈데이터 반환
    null
}
//네트워크 통신에 실패하고 에러 반환
fun <T> UiState<T>.throwableOrNull(): Throwable? = if (this is UiState.Error) {
    //에러 데이터 반환
    error
} else {
    //빈데이터 반환
    null
}