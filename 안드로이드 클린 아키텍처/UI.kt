// View(XML)
class UserProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserProfileBinding
    private val viewModel: UserProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val userId = intent.getStringExtra("USER_ID")
        viewModel.loadUserProfile(userId)
    }
}

// Compose
@Composable
fun UserScreen(viewModel: UserViewModel) {
    val user by viewModel.user.collectAsStateWithLifecycle()

    if (user == null) {
        Text(text = "Loading...")
    } else {
        Text(text = "Hello, ${user.name}")
    }
}