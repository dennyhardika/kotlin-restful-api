package programmer_zaman_now.kotlin_restful_api.service

import org.springframework.web.multipart.MultipartFile
import programmer_zaman_now.kotlin_restful_api.entity.Orders
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import programmer_zaman_now.kotlin_restful_api.model.order.CreateOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.ListOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.ListOrderRequestCtg
import programmer_zaman_now.kotlin_restful_api.model.order.OrderResponse
import programmer_zaman_now.kotlin_restful_api.model.order.UpdateOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.orderfd.CreateOrderfdRequest
import programmer_zaman_now.kotlin_restful_api.model.orderfd.ListOrderfdRequest
import programmer_zaman_now.kotlin_restful_api.model.orderfd.OrderfdResponse
import programmer_zaman_now.kotlin_restful_api.model.orderfd.UpdateOrderfdRequest
import programmer_zaman_now.kotlin_restful_api.model.product.ProductResponse

interface OrderfdService {

    fun create(createOrderfdRequest: CreateOrderfdRequest, fotoOnefd: MultipartFile?, fotoTwofd: MultipartFile?, fotoThreefd: MultipartFile?, fotoFourfd: MultipartFile?, orders: Orders): OrderfdResponse

    fun get(id: Long): OrderfdResponse

    fun getOrderfdByOrder(id_orderfd: Long): List<OrderfdResponse>

    fun update(id: Long, updateOrderfdRequest: UpdateOrderfdRequest, fotoOnefd: MultipartFile?, fotoTwofd: MultipartFile?, fotoThreefd: MultipartFile?, fotoFourfd: MultipartFile?): OrderfdResponse

    fun delete(id: Long)

    fun list(listOrderfdRequest: ListOrderfdRequest): List<OrderfdResponse>

}
