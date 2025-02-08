package programmer_zaman_now.kotlin_restful_api.model.category

import programmer_zaman_now.kotlin_restful_api.model.product.ProductResponse
import java.util.Date

data class CategoryResponse (

    val idkategori: Long,

    val namakategori: String,

    val createdAt: Date,

    val updatedAt: Date?,

    val group: Long

)