import java.time.LocalDateTime

// 쇼핑몰 앱

// Remote
data class OrderResponse(
    @SerializedName("order_id")
    val orderId: String,
    @SerializedName("customer_id")
    val customerId: String,
    @SerializedName("item_list")
    val itemList: List<ItemResponse>,
    @SerializedName("total_amount")
    val totalAmount: Double,
    @SerializedName("order_status")
    val orderStatus: String,
    @SerializedName("created_at")
    val createdAt: String,
)

// Local
data class OrderLocal(
    @PrimaryKey
    val id: String,
    val customer: String,
    val items: String, // JSON 형태로 직렬화된 데이터
    val amount: Double,
    val status: String,
    val timeStamp: Long,
)

// Domain
data class Order(
    val id: String,
    val customerId: String,
    val itemCount: Int,
    val total: Double,
    val createdAt: LocalDateTime,
)

// UI
data class OrderState(
    val orderId: String,
    val itemCount: String, // "3 items"
    val amountText: String, // "$29.99"
    val statusText: String, // "Order in Progress"
)