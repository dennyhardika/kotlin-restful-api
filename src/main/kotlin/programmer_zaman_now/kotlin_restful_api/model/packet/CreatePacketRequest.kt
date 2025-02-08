package programmer_zaman_now.kotlin_restful_api.model.packet

import jakarta.validation.constraints.NotBlank

data class CreatePacketRequest(

    @field:NotBlank
    val namapaket: String,

    @field:NotBlank
    val namakategori: String,

    @field:NotBlank
    val produk1: String,

    val produk2: String? = null,

    val produk3: String? = null,

    val produk4: String? = null,

    @field:NotBlank
    val kethargapaket: String

)
