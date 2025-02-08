package programmer_zaman_now.kotlin_restful_api.model.uprofile

import programmer_zaman_now.kotlin_restful_api.entity.User
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.VehicleType
import java.util.Date

data class UprofileResponse(

    val iduprofile: Long,

    val namalengkap: String,

    val tipekendaraan: String,

    val merekkendaraan: String,

    val noplat: String,

    val alamat: String? = null,

    val nohandphone: String,

    val fotoprofil: String? = null,

    val fotokendaraan: String? = null,

    val createdAt: Date,

    val updatedAt: Date?,

    val user: Long,

    val group: Long,
)
