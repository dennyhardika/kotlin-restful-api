package programmer_zaman_now.kotlin_restful_api.model.uprofile

import programmer_zaman_now.kotlin_restful_api.entity.User
import java.util.Date

data class UprofileResponse(

    val id_uprofile: Long,

    val nama_lengkap: String,

    val jenis_kendaraan: String,

    val alamat: String,

    val no_handphone: String,

    val foto_profil: String,

    val foto_kendaraan: String,

    val createdAt: Date,

    val updatedAt: Date?,

    val user: Long
)
