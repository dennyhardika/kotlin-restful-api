package programmer_zaman_now.kotlin_restful_api.model.orderfd

import java.util.Date

data class OrderfdResponse (

    val idorderfd: Long,

    val waktumulai: String,

    val waktuselesai: String? = null,

    val foto_onefd: String,

    val foto_twofd: String,

    val foto_threefd: String,

    val foto_fourfd: String,

    val keterangan: String? = null,

    val order: Long,

    val createdAt: Date,

    val updatedAt: Date?

)
