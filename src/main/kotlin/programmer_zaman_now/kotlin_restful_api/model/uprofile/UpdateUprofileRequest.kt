package programmer_zaman_now.kotlin_restful_api.model.uprofile

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateUprofileRequest(

    @field:NotBlank
    val namalengkap: String?,

    @field:NotBlank
    val tipekendaraan: String?,

    @field:NotBlank
    val merekkendaraan: String?,

    @field:NotBlank
    val noplat: String?,

    @field:NotBlank
    val alamat: String? = null,

    @field:NotBlank
    val nohandphone: String?,

    @field:NotBlank
    val fotoprofil: String? = null,

    @field:NotBlank
    val fotokendaraan: String? = null,

    @JsonProperty("group")
    @field:NotNull
    val group: Long?

)
