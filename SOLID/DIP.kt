// 문제
class LightBulb {
    fun turnOn() {
        println("Light bulb on")
    }
}

class Switch(private val lightBulb: LightBulb) {
    fun operate() {
        lightBulb.turnOn()
    }
}

// 해결
// 추상화
interface Switchable {
    fun turnOn()
}

// 저수준 모듈
class LightBulb : Switchable {
    override fun turnOn() {
        println("Light bulb on")
    }
}

// 고수준 모듈
class Switch(private val device: Switchable) {
    fun operate() {
        device.turnOn()
    }
}

// 안드로이드 문제
class UserRepository {
    private val localDataSource = LocalDataSource()
    private val remoteDataSource = RemoteDataSource()

    fun getUser(): User {
        return if (localDataSource.hasUser()) {
            localDataSource.getUser()
        } else {
            remoteDataSource.getUser()
        }
    }
}

class LocalDataSource {
    fun hasUser(): Boolean = true
    fun getUser(): User = User("Local User", 25)
}

class RemoteDataSource {
    fun getUser(): User = User("Remote User", 30)
}

// 안드로이드 해결
interface UserDataSource {
    fun getUser(): User
}

class LocalDataSource : UserDataSource {
    fun hasUser(): Boolean = true
    override fun getUser(): User {
        User("Local User", 25)
    }
}

class RemoteDataSource : UserDataSource {
    override fun getUser(): User {
        User("Remote User",30)
    }
}

class UserRepository(
    // 의존성 주입, Dependency Injection, DI
    private val localDataSource: UserDataSource,
    private val remoteDataSource: UserDataSource
) {
    fun getUser(): User {
        return if ((localDataSource as LocalDataSource).hasUser()) {
            localDataSource.getUser()
        } else {
            remoteDataSource.getUser()
        }
    }
}