// Repository Implementation
class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource, // Remote Data Source
    private val userLocalDataSource: USerLocalDataSource, // Local Data Source
    private val userMapper: UserMapper // Model Mapper
) : UserRepository {
    override fun getUserById(userId: String): User {
        // 로컬에서 데이터 가져오기
        val localUser = userLocalDataSource.getUserById(userId)

        // 로컬 데이터가 있으면
        if (localUSer != null) {
            // 로컬 데이터 반환
            return userMapper.mapEntityToDomain(localUser)
        }

        // 로컬 데이터 없으면, 원격 데이터 가져오기
        val remoteUser = userRemoteDataSource.getUserById(userId)

        // 가져온 원격 데이터를 로컬에 저장
        userLocalDataSource.saveUser(remoteUser)

        // 원격 데이터 반환
        return userMapper.mapEntityToDomain(remoteUser)
    }
}

// Local Data Source Interface
interface UserLocalDataSource {
    fun getUserById(userId: String): UserEntity?

    fun saveUser(user: UserEntity)
}

// Remote Data Source Interface
interface UserRemoteDataSource {
    fun getUserById(userId: String): UserEntity
}

// Model Mapper
internal class UserMapper {
    // Data(Entity) -> Domain
    fun mapEntityToDomain(userEntity: UserEntity): User {
        return User(
            id = userEntity.id,
            name = userEntity.name,
            address = userEntity.address,
        )
    }

    // Domain -> Data(Entity)
    fun mapDomainToEntity(user: User) {
        return UserEntity(
            id = user.id,
            name = user.name,
            address = user.address,
        )
    }
}