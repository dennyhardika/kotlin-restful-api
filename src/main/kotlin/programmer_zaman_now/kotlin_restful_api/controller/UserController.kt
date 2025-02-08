package programmer_zaman_now.kotlin_restful_api.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import programmer_zaman_now.kotlin_restful_api.model.WebResponse
import programmer_zaman_now.kotlin_restful_api.model.user.CreateUserRequest
import programmer_zaman_now.kotlin_restful_api.model.user.ListUserRequest
import programmer_zaman_now.kotlin_restful_api.model.user.UpdateUserRequest
import programmer_zaman_now.kotlin_restful_api.model.user.UserResponse
import programmer_zaman_now.kotlin_restful_api.service.UserService

@RestController
class UserController(val userService: UserService) {

    @PostMapping(
        value = ["/api/users"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createUser(@RequestBody body: CreateUserRequest): WebResponse<UserResponse> {
        val userResponse = userService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = userResponse
        )
    }

    @GetMapping(
        value = ["/api/users/{idUser}"],
        produces = ["application/json"]
    )
    fun getUser(@PathVariable("idUser") id: Long): WebResponse<UserResponse> {
        val userResponse = userService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = userResponse
        )
    }

    @PutMapping(
        value = ["api/users/{idUser}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateUser(@PathVariable("idUser") id: Long,
                      @RequestBody updateUserRequest: UpdateUserRequest
    ): WebResponse<UserResponse> {
        val userResponse = userService.update(id, updateUserRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = userResponse
        )
    }

    @DeleteMapping(
        value = ["/api/users/{idUser}"],
        produces = ["application/json"]
    )
    fun deleteUser(@PathVariable("idUser") id: Long): WebResponse<String>{
        val hapus =  userService.delete(id).toString()
        return WebResponse(
            code = 200,
            status = "OK",
            data = hapus
        )
    }

    @GetMapping(
        value = ["/api/users"],
        produces = ["application/json"]
    )
    fun listUsers(@RequestParam(value = "size", defaultValue = "10") size:Int,
                     @RequestParam(value = "page", defaultValue = "0")page:Int): WebResponse<List<UserResponse>> {
        val request = ListUserRequest(page = page, size = size)
        val responses = userService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )

    }
}