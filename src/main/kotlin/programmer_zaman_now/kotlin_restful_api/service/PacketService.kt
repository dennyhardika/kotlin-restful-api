package programmer_zaman_now.kotlin_restful_api.service

import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Group
import programmer_zaman_now.kotlin_restful_api.model.category.CategoryResponse
import programmer_zaman_now.kotlin_restful_api.model.category.CreateCategoryRequest
import programmer_zaman_now.kotlin_restful_api.model.category.ListCategoryRequest
import programmer_zaman_now.kotlin_restful_api.model.category.UpdateCategoryRequest
import programmer_zaman_now.kotlin_restful_api.model.packet.CreatePacketRequest
import programmer_zaman_now.kotlin_restful_api.model.packet.ListPacketRequest
import programmer_zaman_now.kotlin_restful_api.model.packet.PacketResponse
import programmer_zaman_now.kotlin_restful_api.model.packet.UpdatePacketRequest

interface PacketService {

    fun create(createPacketRequest: CreatePacketRequest): PacketResponse

    fun get(id: Long): PacketResponse

    fun getnamapkt(namapaket: String): PacketResponse

    fun update(id: Long, updatePacketRequest: UpdatePacketRequest): PacketResponse

    fun delete(id: Long)

    fun list(listPacketRequest: ListPacketRequest): List<PacketResponse>

}