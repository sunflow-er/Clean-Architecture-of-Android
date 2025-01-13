// ViewModel
class UserViewModel (
	private val getUserUseCase: GetUserUseCase,
) : ViewModel() {
	private val _user = MutableLiveData<User>()
	val user: LiveData<User> get() = _user

	fun loadUser(userId: String) {
		viewModelScope.launch { // Coroutine Scope
			_user.value = getUserUseCase.invoke(userId) // 비동기 작업 및 UI 업데이트
		}
	}
}