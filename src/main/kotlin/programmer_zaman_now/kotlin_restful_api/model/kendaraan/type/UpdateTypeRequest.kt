package programmer_zaman_now.kotlin_restful_api.model.kendaraan.type

import jakarta.validation.constraints.NotBlank

data class UpdateTypeRequest (

    @field:NotBlank
    val tipekendaraan: String?,

    val idmerek: Long?,

    val group: Long?

)