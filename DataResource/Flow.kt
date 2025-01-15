// Cold Flow
fun main() = runBlocking {
    // Cold Flow 생성
    val coldFlow = flow {
        println("Cold Flow: Emitting 1")
        emit(1)
        delay(1000)
        println("Cold Flow: Emitting 2")
        emit(2)
        delay(1000)
        println("Cold Flow: Emitting 3")
        emit(3)
    }

    // 첫 번째 구독자
    println("First collector subscribed")
    coldFlow.collect { emittedData ->
        println("First collector received: $emittedData")
    }

    // 두 번째 구독자
    println("Second collector subscribed")
    coldFlow.collect { emittedData ->
        println("Second collector received: $emittedData")
    }
}

// Hot Flow
fun main() = runBlocking {
    // Hot Flow 생성
    val hotFlow = MutableSharedFlow<Int>() // 또는 MutableStateFlow(0)으로도 생성 가능

    // Coroutine으로 Hot Flow에서 데이터 방출
    launch {
        repeat(3) {
            println("Hot Flow: Emitting ${it + 1}")
            hotFlow.emit(it + 1)
            delay(1000)
        }
    }
    
    // 첫 번째 구독자
    launch {
        println("First collector subscribed")
        hotFlow.collect { emittedData ->
            println("First collector received: $emittedData")
        }
    }

    // 두 번째 구독자 (1초 후에 구독 시작)
    delay(1000)
    launch {
        println("Second collector subscribed")
        hotFlow.collect { emittedData ->
            println("Second collector received: $emittedData")
        }
    }
    
    delay(4000) // Flow의 emit이 완료될 때까지 대기
}