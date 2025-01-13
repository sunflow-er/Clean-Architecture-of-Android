// 문제
open class Bird {
    open fun fly() {
        println("Bird is flying")
    }
}

class penguin : Bird() {
    override fun fly() {
        throw UnsupportedOperationException("Penguins can't fly")
    }
}

// 해결
open class Bird

open class FlyingBird : Bird() {
    open fun fly() {
        println("Bird is flying")
    }
}

class Penguin : Bird() {
    fun swim() {
        println("Penguin is swimming")
    }
}

class Sparrow : FlyingBird() {
    override fun fly() {
        println("Sparrow is flying")
    }
}

// 안드로이드 문제
open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun bind(data: Image) {
        // 기본 데이터 바인딩
    }
}

class TextViewHolder(itemView: View) : BaseViewHolder(itemView) {
    override fun bind(data: Image) {
        throw UnsupportedOperationException("TextViewHolder는 이미지 바인딩을 할 수 없습니다.")
    }
}

// 안드로이드 해결
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T)
}

class TextViewHolder(itemView: View) : BaseViewHolder<String>(itemView) {
    override fun bind(data: String) {
        // 텍스트 데이터 바인딩
    }
}

class ImageViewHolder(itemView: View) : BaseViewHolder<Image>(itemView) {
    override fun bind(data: Image) {
        // 이미지 데이터 바인딩
    }
}