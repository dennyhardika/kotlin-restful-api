package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import programmer_zaman_now.kotlin_restful_api.entity.Orders
import programmer_zaman_now.kotlin_restful_api.entity.Product
import programmer_zaman_now.kotlin_restful_api.entity.orders.Packet

interface OrderRepository : JpaRepository<Orders, Long>{
    fun findByUprofiles_Iduprofile(iduprofile: Long): List<Orders>

    @Query("SELECT o FROM Orders o WHERE LOWER(o.iconorder) = LOWER(:iconorder) AND o.uprofile.id = :uprofileId")
    fun findByIconorderAndUserIdWithPagination(
        @Param("iconorder") iconorder: String,
        @Param("uprofileId") uprofileId: Long,
        pageable: Pageable
    ): Page<Orders>

    @Query("SELECT o FROM Orders o WHERE LOWER(o.iconorder) = LOWER(:iconorder)")
    fun findByIconorderWithPagination(
        @Param("iconorder") iconorder: String,
        pageable: Pageable
    ): Page<Orders>

    @Query("SELECT o FROM Orders o WHERE o.uprofile.id = :uprofileId")
    fun findByUserIdWithPagination(
        @Param("uprofileId") uprofileId: Long,
        pageable: Pageable
    ): Page<Orders>

}
