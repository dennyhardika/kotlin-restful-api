package programmer_zaman_now.kotlin_restful_api.model.product

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateProductRequest (

    @field:NotBlank
    val nama_produk: String

)