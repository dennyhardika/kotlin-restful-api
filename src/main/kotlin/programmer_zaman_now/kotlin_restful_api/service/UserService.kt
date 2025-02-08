package programmer_zaman_now.kotlin_restful_api.service

import programmer_zaman_now.kotlin_restful_api.model.user.CreateUserRequest
import programmer_zaman_now.kotlin_restful_api.model.user.ListUserRequest
import programmer_zaman_now.kotlin_restful_api.model.user.UpdateUserRequest
import programmer_zaman_now.kotlin_restful_api.model.user.UserResponse

interface UserService {

    fun create(createUserRequest: CreateUserRequest): UserResponse

    fun get(id: Long): UserResponse

    fun update(id: Long, updateUserRequest: UpdateUserRequest): UserResponse

    fun delete(id: Long)

    fun list(listUserRequest: ListUserRequest): List<UserResponse>

}