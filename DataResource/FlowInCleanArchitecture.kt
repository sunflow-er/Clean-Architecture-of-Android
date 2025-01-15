// Data
class UserRepository(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) {
    fun getUserById(userId: String): Flow<DataResource<User>> = flow {
        val localUser = localDataSource.getUserById(userId)
        emit(DataResource.Loading(localUser)) // 로컬 데이터 방출 (로딩 상태)

        try {
            val remoteUser = remoteDataSource.getUserById(userId) // 원격 데이터 호출
            localDataSource.saveUser(remoteUser) // 로컬에 저장
            emit(DataResource.Success(remoteUser)) // 성공 상태 방출
        } catch (exception: Exception) {
            emit(DataResource.Error(exception)) // 에러 상태 방출
        }
    }
}

// Domain
class FetchUserUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: String): Flow<DataResource<User>> {
        // Repository에서 Flow를 반환
        return userRepository.getUserById(userId)
    }
}

// Presentation
class UserViewModel(
    private val fetchUserUseCase: FetchUserUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<DataResource<User>>(DataResource.Loading(null))
    val uiState: StateFlow<DataResource<User>> get() = _uiState

    fun fetchUser(userId: String) {
        viewModelScope.launch {
            fetchUserUseCase(userId).collect { resource ->
                _uiState.value = resource // 상태 업데이트
            }
        }
    }
}

// UI
class UserFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { resource ->
                when (resource) {
                    is DataResource.Loading -> showLoading(resource.data) // 로딩 상태 UI
                    is DataResource.Success -> displayUser(resource.data) // 성공 상태 UI
                    is DataResource.Error -> showError(resource.exception) // 에러 상태 UI
                }
            }
        }

        // 사용자 액션
        view.findViewById<Button>(R.id.retryButton).setOnClickListener {
            viewModel.fetchUser("userId123")
        }
    }

    private fun showLoading(user: User?) {
        // 로딩 화면 표시
    }

    private fun displayUser(user: User) {
        // 성공 화면 표시
    }

    private fun showError(exception: Throwable) {
        // 에러 화면 표시
    }
}
