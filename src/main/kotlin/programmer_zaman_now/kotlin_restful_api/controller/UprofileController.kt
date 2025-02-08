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
import programmer_zaman_now.kotlin_restful_api.model.uprofile.CreateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.ListUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UpdateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UprofileResponse
import programmer_zaman_now.kotlin_restful_api.repository.UserRepository
import programmer_zaman_now.kotlin_restful_api.service.UprofileService

@RestController
class UprofileController(val uprofileService: UprofileService, val userRepository: UserRepository) {

    @PostMapping(
        value = ["/api/uprofiles"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createUprofile(@Valid @RequestBody body: CreateUprofileRequest): WebResponse<UprofileResponse> {
        val user = userRepository.findByIdOrNull(body.user)
            ?: throw IllegalArgumentException("User dengan ID ${body.user} tidak ditemukan")

        val uprofileResponse = uprofileService.create(body, user)
        return WebResponse(
            code = 200,
            status = "OK",
            data = uprofileResponse
        )
    }

    @GetMapping(
        value = ["/api/uprofiles/{idUprofile}"],
        produces = ["application/json"]
    )
    fun getUprofile(@PathVariable("idUprofile") id: Long): WebResponse<UprofileResponse> {
        val uprofileResponse = uprofileService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = uprofileResponse
        )
    }
    @PutMapping(
        value = ["/api/uprofiles/{idUprofile}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateUprofile(@PathVariable("idUprofile") id: Long,
                      @RequestBody updateUprofileRequest: UpdateUprofileRequest
    ): WebResponse<UprofileResponse> {
        val uprofileResponse = uprofileService.update(id, updateUprofileRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = uprofileResponse
        )
    }
    @DeleteMapping(
        value = ["/api/uprofiles/{idUprofile}"],
        produces = ["application/json"]
    )
    fun deleteUprofile(@PathVariable("idUprofile") id: Long): WebResponse<String>{
        val hapus =  uprofileService.delete(id).toString()
        return WebResponse(
            code = 200,
            status = "OK",
            data = hapus
        )
    }

    @GetMapping(
        value = ["/api/uprofiles"],
        produces = ["application/json"]
    )
    fun listUprofiles(@RequestParam(value = "size", defaultValue = "10") size:Int,
                     @RequestParam(value = "page", defaultValue = "0")page:Int): WebResponse<List<UprofileResponse>> {
        val request = ListUprofileRequest(page = page, size = size)
        val responses = uprofileService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )

    }
}