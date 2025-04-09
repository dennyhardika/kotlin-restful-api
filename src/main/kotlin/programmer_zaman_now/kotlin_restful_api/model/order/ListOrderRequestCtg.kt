package programmer_zaman_now.kotlin_restful_api.model.order

data class ListOrderRequestCtg(
    val page: Int,
    val size: Int,
    val iconorder: String? = null,
    val uprofileId: Long? = null
)
