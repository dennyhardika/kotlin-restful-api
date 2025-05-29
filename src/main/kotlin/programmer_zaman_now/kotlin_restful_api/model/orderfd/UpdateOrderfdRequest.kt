package programmer_zaman_now.kotlin_restful_api.model.orderfd

import jakarta.validation.constraints.NotBlank

data class UpdateOrderfdRequest(

    @field:NotBlank
    val waktumulai: String,

    val waktuselesai: String? = null,

    val foto_onefd: String? = null,

    val foto_twofd: String? = null,

    val foto_threefd: String? = null,

    val foto_fourfd: String? = null,

    val keterangan: String? = null

)
