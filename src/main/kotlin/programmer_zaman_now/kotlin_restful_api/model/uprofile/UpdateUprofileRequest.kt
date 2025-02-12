package programmer_zaman_now.kotlin_restful_api.model.uprofile

import jakarta.validation.constraints.NotBlank

data class UpdateUprofileRequest(

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
    val fotokendaraan: String?

)
