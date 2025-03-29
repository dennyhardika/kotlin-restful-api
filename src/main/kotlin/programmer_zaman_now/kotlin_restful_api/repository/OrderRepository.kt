package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import programmer_zaman_now.kotlin_restful_api.entity.Orders
import programmer_zaman_now.kotlin_restful_api.entity.orders.Packet

interface OrderRepository : JpaRepository<Orders, Long>{
    fun findByUprofiles_Iduprofile(iduprofile: Long): List<Orders>
}
