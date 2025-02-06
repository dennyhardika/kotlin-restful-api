package programmer_zaman_now.kotlin_restful_api.model.category

import jakarta.validation.constraints.NotBlank

data class CreateCategoryRequest(

    @field:NotBlank
    val namakategori: String?

)
