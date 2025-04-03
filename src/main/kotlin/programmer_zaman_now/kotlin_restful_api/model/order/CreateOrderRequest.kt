package programmer_zaman_now.kotlin_restful_api.model.order

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import programmer_zaman_now.kotlin_restful_api.entity.orders.Packet
import java.util.Date

data class CreateOrderRequest(

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

//
//    val productIds: List<Long>,

//    val packetIds: List<Long>? = emptyList(),
//
//    val promoIds: List<Long>? = emptyList(),


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
    val namabooking: String,

    @JsonProperty("uprofile")
    @field:NotNull
    val uprofile: Long?,


)
