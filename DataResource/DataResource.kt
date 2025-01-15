sealed class DataResource<out T> { // out : 반환(생산)만 할 수 있고, 소비(입력)하지 못하도록 제한
    class Success<T>(val data: T) : DataResource<T>()
    class Error(val throwable: Throwable) : DataResource<Nothing>()
    class Loading<T>(val data: T? = null) : DataResource<T>() // 로딩 중에도 이전 데이터(로컬) 포함할 수 있음

    companion object {
        fun <T> success(data: T) { // 제네릭 함수
            return Success(data)
        }
        fun error(throwable: Throwable) {
            return Error(throwable)
        }
        fun <T> loading(data: T? = null): Loading<T> {
            return Loading(data)
        }
    }
}