package programmer_zaman_now.kotlin_restful_api.service

import programmer_zaman_now.kotlin_restful_api.entity.User
import programmer_zaman_now.kotlin_restful_api.model.uprofile.CreateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.ListUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UpdateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UprofileResponse

interface UprofileService {

    fun create(createUprofileRequest: CreateUprofileRequest, user: User): UprofileResponse

    fun get(id: Long): UprofileResponse

    fun update(id: Long, updateUprofileRequest: UpdateUprofileRequest): UprofileResponse

    fun delete(id: Long)

    fun list(listUprofileRequest: ListUprofileRequest): List<UprofileResponse>

}