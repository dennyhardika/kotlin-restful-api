package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import programmer_zaman_now.kotlin_restful_api.entity.User

interface UserRepository : JpaRepository<User, Long> {

    @Query("SELECT c FROM User c WHERE LOWER(c.username) = LOWER(:username)")
    fun findByUsername(@Param("username") username: String): User?

}
