package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import programmer_zaman_now.kotlin_restful_api.entity.Orders
import programmer_zaman_now.kotlin_restful_api.entity.orders.Ordersfd

interface OrderfdRepository : JpaRepository<Ordersfd, Long> {

    fun findByOrders_IdOrder(idOrder: Long): List<Ordersfd>

}
