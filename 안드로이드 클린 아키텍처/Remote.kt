// Remote Data Source Interface Implementation
internal class UserRemoteDataSourceImpl (
    private val apiService: ApiService,
    private val userMapper: UserMapper,
) : UserRemoteDataSource {
    override suspend fun getUserById(userId: String): UserEntity {
        val remoteUser = apiService.getUserById(userId)
        return userMapper.mapResponseToEntity(remoteUser)
    }
}

// API Interface
interface ApiService {
    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: String) UserResponse
}

// Model Mapper
internal class UserMapper {
    // Response -> Data(Entity)
    fun mapResponseToEntity(userResponse: UserResponse): UserEntity {
        return UserEntity (
            id = userResponse.id,
            name = userResponse.name,
            address = userReponse.address,
        )
    }

    // Data(Entity) -> Response
    fun mapEntityToResponse(userEntity: UserEntity): UserResponse {
        return UserResponse(
            id = userEntity.id,
            name = userEntity.name,
            address = userEntity.address
        )
    }
}