package programmer_zaman_now.kotlin_restful_api.controller

import jakarta.validation.Valid
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import programmer_zaman_now.kotlin_restful_api.model.WebResponse
import programmer_zaman_now.kotlin_restful_api.model.order.CreateOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.ListOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.OrderResponse
import programmer_zaman_now.kotlin_restful_api.model.order.UpdateOrderRequest
import programmer_zaman_now.kotlin_restful_api.repository.ProductRepository
import programmer_zaman_now.kotlin_restful_api.repository.UprofileRepository
import programmer_zaman_now.kotlin_restful_api.service.OrderService

@RestController
class OrderController(val orderService: OrderService, val productRepository: ProductRepository, val uprofileRepository: UprofileRepository) {

    @PostMapping(
        value = ["/api/orders"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createOrder(@Valid @RequestBody body: CreateOrderRequest): WebResponse<OrderResponse> {
        val products = productRepository.findAllById(body.productIds).filterNotNull()
        val uprofile = uprofileRepository.findByIdOrNull(body.id_uprofile)
            ?: throw IllegalArgumentException("User dengan ID ${body.id_uprofile} tidak ditemukan")
        // Pastikan semua produk ditemukan
        if (products.isEmpty()) {
            throw IllegalArgumentException("Produk tidak ditemukan untuk ID: ${body.productIds}")
        }

        if (products.size != body.productIds.size) {
            throw IllegalArgumentException("Beberapa produk tidak ditemukan: ${body.productIds - products.map { it.id_produk }}")
        }

        val orderResponse = orderService.create(body, products, uprofile)
        return WebResponse(
            code = 200,
            status = "OK",
            data = orderResponse
        )
    }

    @GetMapping(
        value = ["/api/orders/{idOrder}"],
        produces = ["application/json"]
    )
    fun getOrder(@PathVariable("idOrder") id: Long): WebResponse<OrderResponse> {
        val orderResponse = orderService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = orderResponse
        )
    }

    @PutMapping(
        value = ["/api/orders/{idOrder}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateOrder(@PathVariable("idOrder") id: Long,
                       @RequestBody updateOrderRequest: UpdateOrderRequest
    ): WebResponse<OrderResponse> {
        val orderResponse = orderService.update(id, updateOrderRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = orderResponse
        )
    }

    @DeleteMapping(
        value = ["/api/orders/{idOrder}"],
        produces = ["application/json"]
    )
    fun deleteOrder(@PathVariable("idOrder") id: Long): WebResponse<String>{
        val hapus =  orderService.delete(id).toString()
        return WebResponse(
            code = 200,
            status = "OK",
            data = hapus
        )
    }

    @GetMapping(
        value = ["/api/orders"],
        produces = ["application/json"]
    )
    fun listOrders(@RequestParam(value = "size", defaultValue = "10") size:Int,
                      @RequestParam(value = "page", defaultValue = "0")page:Int): WebResponse<List<OrderResponse>> {
        val request = ListOrderRequest(page = page, size = size)
        val responses = orderService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )

    }
}