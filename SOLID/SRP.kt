// 문제
class UserManager {
    fun registerUser(user: User) {
        // 사용자 등록 로직
    }

    fun logActivity(activity: String) {
        // 활동 로그 기록 로직
    }

    fun sendEmail(email: String) {
        // 이메일 전송 로직
    }
}

// 해결
class UserService {
    fun registerUser(user: User) {
        // 사용자 등록 로직
    }
}

class ActivityLogger {
    fun logActivity(activity: String) {
        // 활동 로그 기록 로직
    }
}

class EmailService {
    fun sendEmail(email: String) {
        // 이메일 전송 로직
    }
}

// 안드로이드 문제
class MainActivity : AppCompatActivity() {
    fun onCreate() {
        super.onCreate()
        fetchData()
    }

    fun fetchData() {
        // 네트워크 요청
        val data = apiService.getData()

        // 데이터 처리
        val  processedData = processData(data)

        // UI 업데이트
        updateUI(processedData)
    }
}

// 안드로이드 해결
// Data
class DataRepository(private val apiService: ApiService) {
    fun fetchData(): Data {
        return apiService.getData()
    }
}

// Presentation
class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {
    // LiveData는 데이터 변경을 관찰할 수 있는 데이터 홀더 클래스
    // 주로 UI와 ViewModel 간의 데이터 전달에 사용
    val data = MutableLiveData<Data>()

    fun loadData() {
        data.value = dataRepository.fetchData()
    }
}

// UI
class MainActivity : AppCompatActivity() {

    // viewModels()에게 ViewModel 인스턴스 관리를 위임
    // ViewModelProvider를 사용해 ViewModel의 인스턴스를 생성하거나 가져옴
    // ViewModel을 액티비티 또는 프래그먼트의 생명 주기에 맞게 관리
    private val viewModel: MainViewModel by viewModels()

    fun onCreate() {
        super.onCreate()
        viewModel.data
            .observe( // 데이터를 관찰(observe)하도록 설정
                this // LifecycleOwner
            ) { data -> // Callback function
                updateUI(data)
            }
        viewModel.loadData()
    }
}