package programmer_zaman_now.kotlin_restful_api.model.order

import jakarta.validation.constraints.NotBlank
import java.util.Date

data class OrderResponse(

    val idorder: Long,

    val iconorder: String? = null,

    val kategori1: String? = null,

    val produk1a: String? = null,

    val produk1b: String? = null,

    val produk1c: String? = null,

    val produk1d: String? = null,

    val kategori2: String? = null,

    val produk2a: String? = null,

    val produk2b: String? = null,

    val produk2c: String? = null,

    val produk2d: String? = null,

    val tanggalkedatangan: String,

    val keterangan: String? = null,

    val foto_one: String,

    val foto_two: String,

    val statusbooking: String,

    val tipebooking: String,

    val namabooking: String,

    val uprofile: Long,

    val createdAt: Date,

    val updatedAt: Date?

    //
//    val productIds: List<Long?>,

//    val packetIds: List<Long>? = emptyList(), // Bisa kosong atau tidak dikirim
//
//    val promoIds: List<Long>? = emptyList(), // Bisa kosong atau tidak dikirim
)
