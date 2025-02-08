package programmer_zaman_now.kotlin_restful_api.model.user

import jakarta.validation.constraints.NotBlank

data class UpdateUserRequest(

    @field:NotBlank
    val username: String?,

    @field:NotBlank
    val password: String?

)
