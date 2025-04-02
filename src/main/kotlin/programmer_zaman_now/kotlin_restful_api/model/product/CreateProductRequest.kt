package programmer_zaman_now.kotlin_restful_api.model.product

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateProductRequest(


        @field:NotBlank
        val namaproduk: String,

        @field:NotNull
        val idkategori: Long

)
