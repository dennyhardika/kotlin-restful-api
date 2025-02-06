package programmer_zaman_now.kotlin_restful_api.model.category

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import programmer_zaman_now.kotlin_restful_api.model.product.UpdateProductRequest

data class UpdateCategoryRequest(

    @field:NotBlank
    val namakategori: String?

)
