package programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand

import java.util.Date

data class BrandResponse(

    val idmerek: Long,

    val merekkendaraan: String,

    val createdAt: Date,

    val updatedAt: Date?

)
