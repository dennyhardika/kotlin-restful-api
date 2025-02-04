package programmer_zaman_now.kotlin_restful_api.model.order

import java.util.Date

data class OrderResponse(

    val id_order: Long,

    val id_uprofile: Long,

    val productIds: List<Long?>,

    val startedAt: Date?,

    val finishedAt: Date?,

    val keterangan: String,

    val foto_one: String,

    val foto_two: String,

    val status: String,

    val createdAt: Date,

    val updatedAt: Date?
)
