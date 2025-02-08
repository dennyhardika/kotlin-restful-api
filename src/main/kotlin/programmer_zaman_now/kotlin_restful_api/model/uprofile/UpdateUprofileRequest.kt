package programmer_zaman_now.kotlin_restful_api.model.uprofile

import jakarta.validation.constraints.NotBlank

data class UpdateUprofileRequest(

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
    val foto_kendaraan: String?

)
