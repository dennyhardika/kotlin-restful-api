package programmer_zaman_now.kotlin_restful_api.model.kendaraan.group

import java.util.Date

data class GroupResponse(

    val idgrup: Long,

    val grupkendaraan: String,

    val createdAt: Date,

    val updatedAt: Date?

    )
