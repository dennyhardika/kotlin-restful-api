package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import programmer_zaman_now.kotlin_restful_api.entity.User
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.user.CreateUserRequest
import programmer_zaman_now.kotlin_restful_api.model.user.ListUserRequest
import programmer_zaman_now.kotlin_restful_api.model.user.UpdateUserRequest
import programmer_zaman_now.kotlin_restful_api.model.user.UserResponse
import programmer_zaman_now.kotlin_restful_api.repository.UserRepository
import programmer_zaman_now.kotlin_restful_api.service.UserService
import java.util.Date
import java.util.stream.Collectors

@Service
class UserServiceImpl(val userRepository: UserRepository): UserService {
    override fun create(createUserRequest: CreateUserRequest): UserResponse {
        val user = User(
            username = createUserRequest.username!!,
            password = createUserRequest.password!!,
            createdAt = Date(),
            updatedAt = null
        )

        userRepository.save(user)

        return convertUserToUserResponse(user)
    }

    override fun get(id: Long): UserResponse {
        val user = findUserByOrThrowNotFound(id)
        return convertUserToUserResponse(user)
    }

    override fun update(id: Long, updateUserRequest: UpdateUserRequest): UserResponse {
        val user = findUserByOrThrowNotFound(id)

        user.apply {
            username = updateUserRequest.username!!
            password = updateUserRequest.password!!
            updatedAt = Date()
        }

        userRepository.save(user)

        return convertUserToUserResponse(user)
    }

    override fun delete(id: Long) {
        val user = findUserByOrThrowNotFound(id)
        userRepository.delete(user)
    }

    override fun list(listUserRequest: ListUserRequest): List<UserResponse> {
        val page = userRepository.findAll(PageRequest.of(listUserRequest.page, listUserRequest.size))
        val users: List<User> = page.get().collect(Collectors.toList())

        return users.map { convertUserToUserResponse(it) }
    }

    private fun findUserByOrThrowNotFound(id: Long): User {
        val user = userRepository.findByIdOrNull(id)
        if (user == null){
            throw NotFoundExpection()
        }else {
            return user;
        }
    }
    private fun convertUserToUserResponse(user: User): UserResponse {
        return UserResponse(
            iduser = user.iduser!!,
            username = user.username,
            password = user.password,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt
        )
    }
}