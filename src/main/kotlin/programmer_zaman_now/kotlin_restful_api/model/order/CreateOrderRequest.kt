package programmer_zaman_now.kotlin_restful_api.model.order

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.Date

data class CreateOrderRequest(

    @JsonProperty("id_uprofile")
    @field:NotNull
    val id_uprofile: Long?,

    val productIds: List<Long>,

    @field:NotNull
    val startedAt: Date,

    @field:NotNull
    val finishedAt: Date,

    @field:NotBlank
    val keterangan: String,

    @field:NotBlank
    val foto_one: String,

    @field:NotBlank
    val foto_two: String,

    @field:NotBlank
    val status: String

)
