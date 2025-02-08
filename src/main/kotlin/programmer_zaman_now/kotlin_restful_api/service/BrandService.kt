package programmer_zaman_now.kotlin_restful_api.service

import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.BrandResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.CreateBrandRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.ListBrandRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.UpdateBrandRequest

interface BrandService {

    fun create(createBrandRequest: CreateBrandRequest): BrandResponse

    fun get(id: Long): BrandResponse

    fun getmerekknd(merekkendaraan: String): BrandResponse

    fun update(id: Long, updateBrandRequest: UpdateBrandRequest): BrandResponse

    fun delete(id: Long)

    fun list(listBrandRequest: ListBrandRequest): List<BrandResponse>
}