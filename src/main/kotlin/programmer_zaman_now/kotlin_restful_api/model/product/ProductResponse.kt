package programmer_zaman_now.kotlin_restful_api.model.product

import java.util.Date

data class ProductResponse(

    val id_produk: Long,

    val nama_produk: String,

    val createdAt: Date,

    val updatedAt: Date?,

    val category: Long

)
