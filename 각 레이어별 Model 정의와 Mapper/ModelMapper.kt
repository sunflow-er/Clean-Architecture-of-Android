// 별도의 Model Mapper 클래스
internal class UserMapper {
    fun mapEntityToDomain(userEntity: UserEntity): User {
        return User(
            id = userEntity.id,
            name = userEntity.name,
            address = userEntity.address,
        )
    }

    fun mapDomainToEntity(user: User): UserEntity {
        return UserEntity(
            id = user.id,
            name = user.name,
            address = user.address
        )
    }
}

// Kotlin 확장함수 활용
fun Movie.toPresentation(): MovieModel {
    return MovieModel(
        id,
        name,
        description,
        posterImageUrl,
        backdropImageUrl,
        videoUrl,
        rating,
        rateCount,
        releasedAt,
        runtime,
        genres.map { it.toPresentations() },
    )
}

// Mapper 추상화
interface RemoteMapper<DataModel> {
    fun toData(): DataModel
}

interface DataMapper<DomainModel> {
    fun toDomain(): DomainModel
}

data class UserResponse(
    val id: String,
    val name: String,
    val address: String,
) : RemoteMapper<UserEntity> {
    override fun toData(): UserEntity {
        return UserEntity(
            id = id,
            name = name,
            address = address,
        )
    }
}

data class UserEntity(
    val id: String,
    val name: String,
    val address: String,
) : DataMapper<User> {
    override fun toDomain(): User {
        return User(
            id = id,
            name = name,
            address = address,
        )
    }
}

internal class UserRemoteDataSourceImpl(
    private val apiService: ApiService,
) : UserRemoteDataSource {
    override suspend fun getUserById(userId: String): UserEntity {
        val remoteUser = apiService.getUserById(userId)
        return remoteUser.toData()
    }
}