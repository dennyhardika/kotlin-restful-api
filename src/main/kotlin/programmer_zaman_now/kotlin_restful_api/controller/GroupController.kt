package programmer_zaman_now.kotlin_restful_api.controller

import jakarta.validation.Valid
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import programmer_zaman_now.kotlin_restful_api.model.WebResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.group.CreateGroupRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.group.GroupResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.group.ListGroupRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.group.UpdateGroupRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.CreateTypeRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.ListTypeRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.TypeResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.UpdateTypeRequest
import programmer_zaman_now.kotlin_restful_api.service.GroupService

@RestController
class GroupController (val groupService: GroupService ) {

    @PostMapping(
        value = ["/api/groups"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createGroup(@Valid @RequestBody body: CreateGroupRequest): WebResponse<GroupResponse> {
//        val grup = brandRepository.findByIdOrNull(body.brand)
//            ?: throw IllegalArgumentException("Brand dengan ID ${body.brand} tidak ditemukan")

        val groupResponse = groupService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = groupResponse
        )
    }

    @GetMapping(
        value = ["/api/groups/name/{namagruo}"],
        produces = ["application/json"]
    )
    fun getGroupName(@PathVariable("namagruo") grupknd: String): WebResponse<GroupResponse> {
        val groupResponse = groupService.getgrupknd(grupknd)
        return WebResponse(
            code = 200,
            status = "OK",
            data = groupResponse
        )
    }

    @GetMapping(
        value = ["/api/groups/id/{idgrup}"],
        produces = ["application/json"]
    )
    fun getGroup(@PathVariable("idgrup") id: Long): WebResponse<GroupResponse> {
        val groupResponse = groupService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = groupResponse
        )
    }

    @PutMapping(
        value = ["/api/groups/{idgrup}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateVehicleType(
        @PathVariable("idgrup") id: Long,
        @RequestBody updateGroupRequest: UpdateGroupRequest
    ): WebResponse<GroupResponse> {
        val groupResponse = groupService.update(id, updateGroupRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = groupResponse
        )
    }

    @DeleteMapping(
        value = ["/api/groups/{idgrup}"],
        produces = ["application/json"]
    )
    fun deleteGroup(@PathVariable("idgrup") id: Long): WebResponse<String>{
        val hapus =  groupService.delete(id).toString()
        return WebResponse(
            code = 200,
            status = "OK",
            data = hapus
        )
    }

    @GetMapping(
        value = ["/api/groups"],
        produces = ["application/json"]
    )
    fun listGroup(@RequestParam(value = "size", defaultValue = "10") size:Int,
                        @RequestParam(value = "page", defaultValue = "0")page:Int): WebResponse<List<GroupResponse>> {
        val request = ListGroupRequest(page = page, size = size)
        val responses = groupService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )

    }

}