package programmer_zaman_now.kotlin_restful_api.model.kendaraan.group

import jakarta.validation.constraints.NotBlank

data class CreateGroupRequest(

    @field:NotBlank
    val grupkendaraan: String?,

)
