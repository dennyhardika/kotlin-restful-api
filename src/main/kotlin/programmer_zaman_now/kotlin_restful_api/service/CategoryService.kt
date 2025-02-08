package programmer_zaman_now.kotlin_restful_api.service

import org.springframework.stereotype.Service
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Group
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.VehicleType
import programmer_zaman_now.kotlin_restful_api.model.category.ListCategoryRequest
import programmer_zaman_now.kotlin_restful_api.model.category.CategoryResponse
import programmer_zaman_now.kotlin_restful_api.model.category.CreateCategoryRequest
import programmer_zaman_now.kotlin_restful_api.model.category.UpdateCategoryRequest

interface CategoryService {

    fun create(createCategoryRequest: CreateCategoryRequest, group: Group): CategoryResponse

    fun get(id: Long): CategoryResponse

    fun getnamaktg(namakategori: String): CategoryResponse

    fun update(id: Long, updateCategoryRequest: UpdateCategoryRequest): CategoryResponse

    fun delete(id: Long)

    fun list(listCategoryRequest: ListCategoryRequest): List<CategoryResponse>
}