package programmer_zaman_now.kotlin_restful_api.service

import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Brand
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.group.CreateGroupRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.group.GroupResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.group.ListGroupRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.group.UpdateGroupRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.CreateTypeRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.ListTypeRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.TypeResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.UpdateTypeRequest

interface GroupService {

    fun create(createGroupRequest: CreateGroupRequest): GroupResponse

    fun get(id: Long): GroupResponse

    fun getgrupknd(grupkendaraan: String): GroupResponse

    fun update(id: Long, updateGroupRequest: UpdateGroupRequest): GroupResponse

    fun delete(id: Long)

    fun list(listGroupRequest: ListGroupRequest): List<GroupResponse>

}