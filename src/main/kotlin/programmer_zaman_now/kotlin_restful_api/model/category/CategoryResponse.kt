package programmer_zaman_now.kotlin_restful_api.model.category

import programmer_zaman_now.kotlin_restful_api.model.product.ProductResponse
import java.util.Date

data class CategoryResponse (

    val id_kategori: Long,

    val nama_kategori: String,

    val createdAt: Date,

    val updatedAt: Date?

)