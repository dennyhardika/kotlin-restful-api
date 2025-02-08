package programmer_zaman_now.kotlin_restful_api.model.kendaraan.type

import java.util.Date

data class TypeResponse(

    val idtipe: Long,

    val tipekendaraan: String,

    val createdAt: Date,

    val updatedAt: Date?,

    val brand: Long,

    val group: Long
)
