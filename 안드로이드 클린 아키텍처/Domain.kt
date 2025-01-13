// Model
data class User(
    val id: String,
    val name: String,
    val address: String,
)

// UseCase Interface
interface GetUserUseCase {
    fun invoke(userId: String): User
}

// UseCase Implementation
class GetUserUseCaseImpl(private val userRepository: UserRepository) : GetUserUseCase {
    override fun invoke(userId: String): User {
        return userRepository.getUserById(userId)
    }
}

// Repository Interface
interface UserRepository {
    fun getUserById(id: String): User
}