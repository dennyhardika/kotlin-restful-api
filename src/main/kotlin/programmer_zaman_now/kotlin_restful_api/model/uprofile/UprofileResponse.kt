package programmer_zaman_now.kotlin_restful_api.model.uprofile

import programmer_zaman_now.kotlin_restful_api.entity.User
import java.util.Date

data class UprofileResponse(

    val iduprofile: Long,

    val namalengkap: String,

    val jeniskendaraan: String,

    val merekkendaraan: String,

    val noplat: String,

    val alamat: String,

    val nohandphone: String,

    val fotoprofil: String,

    val fotokendaraan: String,

    val createdAt: Date,

    val updatedAt: Date?,

    val user: Long
)
