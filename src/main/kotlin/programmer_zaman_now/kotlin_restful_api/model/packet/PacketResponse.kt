package programmer_zaman_now.kotlin_restful_api.model.packet

import jakarta.validation.constraints.NotBlank
import java.util.Date

data class PacketResponse(

    val idpaket: Long,

    val namapaket: String,

    val namakategori: String,

    val produk1: String,

    val produk2: String? = null,

    val produk3: String? = null,

    val produk4: String? = null,

    val kethargapaket: String,

    val createdAt: Date,

    val updatedAt: Date?

    )
