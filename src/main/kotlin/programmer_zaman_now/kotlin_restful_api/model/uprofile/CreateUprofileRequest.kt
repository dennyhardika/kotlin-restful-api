package programmer_zaman_now.kotlin_restful_api.model.uprofile

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import programmer_zaman_now.kotlin_restful_api.entity.User

data class CreateUprofileRequest (

    @field:NotBlank
    val nama_lengkap: String?,

    @field:NotBlank
    val jenis_kendaraan: String?,

    @field:NotBlank
    val alamat: String?,

    @field:NotBlank
    val no_handphone: String?,

    @field:NotBlank
    val foto_profil: String?,

    @field:NotBlank
    val foto_kendaraan: String?,

    @JsonProperty("user")
    @field:NotNull
    val user: Long?

)