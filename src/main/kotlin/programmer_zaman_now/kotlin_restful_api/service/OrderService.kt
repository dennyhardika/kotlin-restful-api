package programmer_zaman_now.kotlin_restful_api.service

import programmer_zaman_now.kotlin_restful_api.entity.Product
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import programmer_zaman_now.kotlin_restful_api.model.order.CreateOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.ListOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.OrderResponse
import programmer_zaman_now.kotlin_restful_api.model.order.UpdateOrderRequest

interface OrderService {

    fun create(createOrderRequest: CreateOrderRequest, products: List<Product>, uprofile: Uprofile): OrderResponse

    fun get(id: Long): OrderResponse

    fun update(id: Long, updateOrderRequest: UpdateOrderRequest): OrderResponse

    fun delete(id: Long)

    fun list(listOrderRequest: ListOrderRequest): List<OrderResponse>

}