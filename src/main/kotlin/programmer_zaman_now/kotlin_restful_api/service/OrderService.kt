package programmer_zaman_now.kotlin_restful_api.service

import org.springframework.web.multipart.MultipartFile
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import programmer_zaman_now.kotlin_restful_api.model.order.CreateOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.ListOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.OrderResponse
import programmer_zaman_now.kotlin_restful_api.model.order.UpdateOrderRequest

interface OrderService {

    fun create(createOrderRequest: CreateOrderRequest, fotoOne: MultipartFile?, fotoTwo: MultipartFile?, uprofile: Uprofile): OrderResponse // , products: List<Product>, uprofile: Uprofile

    fun get(id: Long): OrderResponse

    fun update(id: Long, updateOrderRequest: UpdateOrderRequest, fotoOne: MultipartFile?, fotoTwo: MultipartFile?): OrderResponse

    fun delete(id: Long)

    fun list(listOrderRequest: ListOrderRequest): List<OrderResponse>

}