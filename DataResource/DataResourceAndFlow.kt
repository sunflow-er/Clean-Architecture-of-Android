import java.lang.Exception

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

// Cold Flow
override fun getUser(userId: String): Flow<DataResource<User>> {
    return flow {
        emit(DataResource.loading()) // loading

        try {
            val user = userRemoteDataSource.getUserById(userId)
            emit(DataResource.success(user.toDomain())) // success
        } catch (exception: Exception) {
            emit(DataResource.error(exception)) // error
        }
    }
}

// Local
override fun getUser(userId: String): Flow<DataResource<User>> {
    return flow {
        emit(DataResource.loading()) // loading

        try {
            val localUser = userLocalDataSource.getUserById(userId)
            if (localUser != null) { // 로컬에 유저가 있으면
                emit(DataResource.success(localUser.toDomain())) // success
            }
            val remoteUser = userRemoteDataSource.getUserById(userId) // 원격에서 가져옴
            userLocalDataSource.saveUser(remoteUser) // 로컬에 저장
            emit(DataResource.success(remoteUser.toDomain())) // success
        } catch (exception: Exception) {
            emit(DataResource.error(exception)) // error
        }
    }
}

override fun getUser(userId: String): Flow<DataResource<User>> {
    return flow {
        val localUser = userLocalDataSource.getUserById(userId) // 로컬 데이터 가져옴
        emit(DataResource.loading(localUser?.toDomain())) // loading, 캐시가 되어있는 상태를 먼저 보여주지만 현재 로딩 중
        
        try {
            val remoteUser = userRemoteDataSource.getUserById(userId) // 원격 데이터 가져옴
            userLocalDataSource.saveUser(remoteUser) // 로컬에 저장
            emit(DataResource.success(remoteUser.toDomain())) // success, Remote 데이터로 교체
        } catch (exception: Exception) {
            emit(DataResource.error(exception)) // error
        }
    }
}