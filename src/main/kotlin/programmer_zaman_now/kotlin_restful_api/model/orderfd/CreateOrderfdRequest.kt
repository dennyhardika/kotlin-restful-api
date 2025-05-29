package programmer_zaman_now.kotlin_restful_api.model.orderfd

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import programmer_zaman_now.kotlin_restful_api.entity.orders.Ordersfd

data class CreateOrderfdRequest(

    @field:NotBlank
    val waktumulai: String,

    val waktuselesai: String? = null,

    val foto_onefd: String? = null,

    val foto_twofd: String? = null,

    val foto_threefd: String? = null,

    val foto_fourfd: String? = null,

    val keterangan: String? = null,

    @JsonProperty("order")
    @field:NotNull
    val order: Long?,

)
