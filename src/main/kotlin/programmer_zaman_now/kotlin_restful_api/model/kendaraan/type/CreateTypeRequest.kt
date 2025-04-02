package programmer_zaman_now.kotlin_restful_api.model.kendaraan.type

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateTypeRequest(

    @field:NotBlank
    val tipekendaraan: String?,

    @JsonProperty("brand")
    @field:NotNull
    val brand: Long?,

    @JsonProperty("group")
    @field:NotNull
    val idgrup: Long?

)
