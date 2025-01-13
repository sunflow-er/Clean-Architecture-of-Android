// 문제
class PaymentProcessor {
    fun processPayment(type: String) {
        when (type) {
            "CreditCard" -> processCreditCard()
            "PayPal" -> processPayPal()
            // 새로운 결제 수단 추가 시 코드 수정 필요
        }
    }
}

// 해결
interface PaymentMethod() {
    fun pay()
}

class CreditCardPayment : PaymentMethod {
    override fun pay() {
        // 신용카드 결제 로직
    }
}

class PayPalPayment : PaymentMethod {
    override fun pay() {
        // PayPal 결제 로직
    }
}

class PaymentProcessor {
    fun processPayment(paymentMethod: PaymentMethod) {
        paymentMethod.pay()
    }
}

// 안드로이드 문제
class ItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TextViewHolder -> holder.bind(textData[position])
            is ImageViewHolder -> holder.bind(imageData[position])
        }
    }
}

// 안드로이드 해결
abstract class BaseViewHolder<T> (itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data : T)
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

class ItemAdapter<T>(
    private val items: List<T>,
    private val viewTypeToViewHolder: Map<Int, BaseViewHolder<T>>
) : RecyclerView.Adapter<BaseViewHolder<T>>() {
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(items[position])
    }
}





