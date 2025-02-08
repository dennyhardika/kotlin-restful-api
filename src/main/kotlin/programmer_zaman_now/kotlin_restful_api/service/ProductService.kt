package programmer_zaman_now.kotlin_restful_api.service

import programmer_zaman_now.kotlin_restful_api.entity.Category
import programmer_zaman_now.kotlin_restful_api.entity.User
import programmer_zaman_now.kotlin_restful_api.model.product.CreateProductRequest
import programmer_zaman_now.kotlin_restful_api.model.product.ListProductRequest
import programmer_zaman_now.kotlin_restful_api.model.product.ProductResponse
import programmer_zaman_now.kotlin_restful_api.model.product.UpdateProductRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.CreateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.ListUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UpdateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UprofileResponse

interface ProductService {

    fun create(createProductRequest: CreateProductRequest, category: Category): ProductResponse

    fun get(id: Long): ProductResponse

    fun update(id: Long, updateProductRequest: UpdateProductRequest): ProductResponse

    fun delete(id: Long)

    fun list(listProductRequest: ListProductRequest): List<ProductResponse>

}