package programmer_zaman_now.kotlin_restful_api.model.order

import jakarta.validation.constraints.NotBlank
import java.util.Date

data class UpdateOrderRequest (

    @field:NotBlank
    val productIds: List<Long>,

    @field:NotBlank
    val startedAt: Date?,

    @field:NotBlank
    val finishedAt: Date?,

    @field:NotBlank
    val keterangan: String?,

    @field:NotBlank
    val foto_one: String?,

    @field:NotBlank
    val foto_two: String?,

    @field:NotBlank
    val status: String?

)