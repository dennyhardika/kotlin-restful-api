package programmer_zaman_now.kotlin_restful_api.model.uprofile

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import programmer_zaman_now.kotlin_restful_api.entity.User

data class CreateUprofileRequest (

    @field:NotBlank
    val namalengkap: String?,

    @field:NotBlank
    val jeniskendaraan: String?,

    @field:NotBlank
    val merekkendaraan: String?,

    @field:NotBlank
    val noplat: String?,

    @field:NotBlank
    val alamat: String?,

    @field:NotBlank
    val nohandphone: String?,

    @field:NotBlank
    val fotoprofil: String?,

    @field:NotBlank
    val fotokendaraan: String?,

    @JsonProperty("user")
    @field:NotNull
    val user: Long?

)
