// 문제
interface Worker {
    fun work()
    fun eat()
}

class Human : Worker {
    override fun work() {
        println("Human working")
    }

    override fun eat() {
        println("Human eating")
    }
}

class Robot : Worker {
    override fun work() {
        println("Robot working")
    }

    override fun eat() {
        throw UnsupportedOperationException("Robot can't eat")
    }
}

// 해결
interface Workable {
    fun work()
}

interface Eatable {
    fun eat()
}

class Human : Workable, Eatable {
    override fun work() {
        println("Human working")
    }

    override fun eat() {
        println("Human eating")
    }
}

class Robot : Workable {
    override fun work() {
        println("Robot working")
    }
}

// 안드로이드 문제
interface ItemTouchListener {
    fun onClick()
    fun onLongClick()
    fun onSwipe()
}

class SimpleItem : ItemTouchListener {
    override fun onClick() {
        // 클릭 처리
    }

    override fun onLongClick() {
        // 사용하지 않음
    }

    override fun onSwipe() {
        // 사용하지 않음
    }
}

// 안드로이드 해결
interface Clickable {
    fun onClick()
}

interface LongClickable {
    fun onLongClick()
}

interface Swipeable {
    fun onSwipe()
}

class SimpleItem : Clickable {
    override fun onClick() {
        // 클릭 처리
    }
}