package programmer_zaman_now.kotlin_restful_api.service

import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Brand
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Group
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.BrandResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.CreateBrandRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.ListBrandRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.UpdateBrandRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.CreateTypeRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.ListTypeRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.TypeResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.UpdateTypeRequest

interface VehicleTypeService {

    fun create(createTypeRequest: CreateTypeRequest, brand: Brand, group: Group): TypeResponse

    fun get(id: Long): TypeResponse

    fun gettipeknd(tipekendaraan: String): TypeResponse

    fun update(id: Long, updateTypeRequest: UpdateTypeRequest): TypeResponse

    fun delete(id: Long)

    fun list(listTypeRequest: ListTypeRequest): List<TypeResponse>

}