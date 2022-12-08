package ksg.project.commutingguide.utils


//빈 데이터 익셉션 처리
class EmptyBodyException(message: String? = "") : Exception(message)
//네트워크 익셉션 처리
class NetworkFailureException(message: String? = "") : Exception(message)