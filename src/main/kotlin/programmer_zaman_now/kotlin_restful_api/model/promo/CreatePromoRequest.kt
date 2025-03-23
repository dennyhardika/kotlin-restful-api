package programmer_zaman_now.kotlin_restful_api.model.promo

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreatePromoRequest(

    @field:NotBlank
    val namapromo: String,

    @field:NotBlank
    val kethargapromo: String,

    @field:NotBlank
    val ketpromo: String,

    @field:NotBlank
    val namakategori: String,

    @field:NotBlank
    val exppromo: String,

    @field:NotBlank
    val iconpromo: String

//    @JsonProperty("order")
//    @field:NotNull
//    val order: Long,

//    @JsonProperty("kategori")
//    @field:NotNull
//    val category: Long,

//    @field:NotNull
//    val product: List<Long>? = emptyList()

)
