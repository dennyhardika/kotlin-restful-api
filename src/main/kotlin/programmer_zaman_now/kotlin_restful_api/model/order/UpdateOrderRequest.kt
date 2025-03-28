package programmer_zaman_now.kotlin_restful_api.model.order

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.Date

data class UpdateOrderRequest (

    @field:NotBlank
    val iconorder: String,

    @field:NotBlank
    val kategori1: String,

    @field:NotBlank
    val produk1a: String,

    val produk1b: String? = null,

    val produk1c: String? = null,

    val produk1d: String? = null,

    val kategori2: String? = null,

    val produk2a: String? = null,

    val produk2b: String? = null,

    val produk2c: String? = null,

    val produk2d: String? = null,

//    @field:NotBlank
//    val productIds: List<Long>,

//    val packetIds: List<Long>? = emptyList(),
//
//    val promoIds: List<Long>? = emptyList(),

    @field:NotBlank
    val startedAt: String,

    @field:NotBlank
    val finishedAt: String,

    @field:NotBlank
    val tanggalkedatangan: String,

    val keterangan: String? = null,

    val foto_one: String? = null,

    val foto_two: String? = null,

    @field:NotBlank
    val statusbooking: String,

    @field:NotBlank
    val tipebooking: String,

    @field:NotBlank
    val namabooking: String

)
