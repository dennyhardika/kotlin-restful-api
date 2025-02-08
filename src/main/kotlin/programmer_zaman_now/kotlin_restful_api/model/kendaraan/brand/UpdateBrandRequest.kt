package programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand

import jakarta.validation.constraints.NotBlank

data class UpdateBrandRequest(

    @field:NotBlank
    val merekkendaraan: String?
)
