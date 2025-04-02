package programmer_zaman_now.kotlin_restful_api.model.kendaraan.type

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateTypeRequest (

    @field:NotBlank
    val tipekendaraan: String?,

    @field:NotNull
    val brand: Long?,

    @field:NotNull
    val group: Long?

)
