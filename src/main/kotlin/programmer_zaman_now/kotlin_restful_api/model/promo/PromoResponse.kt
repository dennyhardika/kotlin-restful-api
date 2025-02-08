package programmer_zaman_now.kotlin_restful_api.model.promo

import jakarta.validation.constraints.NotBlank
import java.util.Date

data class PromoResponse(

    val idpromo: Long,

    val namapromo: String,

    val kethargapromo: String,

    val ketpromo: String,

    val createdAt: Date,

    val updatedAt: Date?,

    val namakategori: String,

    val exppromo: String

//    val promo: List<Long>? = emptyList(), // Bisa kosong atau tidak dikirim

)
