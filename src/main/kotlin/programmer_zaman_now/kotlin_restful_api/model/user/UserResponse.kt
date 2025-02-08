package programmer_zaman_now.kotlin_restful_api.model.user

import java.util.Date

data class UserResponse (

    val id_user: Long,

    val username: String,

    val password: String,

    val createdAt: Date,

    val updatedAt: Date?

)