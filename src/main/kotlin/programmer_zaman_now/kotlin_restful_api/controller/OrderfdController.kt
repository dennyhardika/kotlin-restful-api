package programmer_zaman_now.kotlin_restful_api.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import programmer_zaman_now.kotlin_restful_api.model.WebResponse
import programmer_zaman_now.kotlin_restful_api.model.order.ListOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.OrderResponse
import programmer_zaman_now.kotlin_restful_api.model.orderfd.CreateOrderfdRequest
import programmer_zaman_now.kotlin_restful_api.model.orderfd.ListOrderfdRequest
import programmer_zaman_now.kotlin_restful_api.model.orderfd.OrderfdResponse
import programmer_zaman_now.kotlin_restful_api.model.orderfd.UpdateOrderfdRequest
import programmer_zaman_now.kotlin_restful_api.model.product.ProductResponse
import programmer_zaman_now.kotlin_restful_api.repository.OrderRepository
import programmer_zaman_now.kotlin_restful_api.service.FileStorageService
import programmer_zaman_now.kotlin_restful_api.service.OrderfdService

@RestController
class OrderfdController(val orderfdService: OrderfdService, val orderRepository: OrderRepository, val fileStorageService: FileStorageService) {

    @PostMapping(
        value = ["/api/ordersfd"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"] // Ubah menjadi multipart
    )
    fun createOrderfd(
        @RequestParam("waktumulai") waktumulai: String,
        @RequestParam("waktuselesai") waktuselesai: String,
        @RequestParam("keteranganfd") keteranganfd: String?,
        @RequestParam("foto_onefd", required = false) foto_onefd: MultipartFile?, // Ubah ke opsional
        @RequestParam("foto_twofd", required = false) foto_twofd: MultipartFile?, // Ubah ke opsional
        @RequestParam("foto_threefd", required = false) foto_threefd: MultipartFile?, // Ubah ke opsional
        @RequestParam("foto_fourfd", required = false) foto_fourfd: MultipartFile?, // Ubah ke opsional
        @RequestParam("order") orderfdStr: String
    ): WebResponse<OrderfdResponse> {
        // Mengonversi uprofileStr (String) menjadi Long
        val orderfdId = orderfdStr.toLongOrNull()
            ?: throw IllegalArgumentException("User profile ID tidak valid: $orderfdStr")

        val orderT = orderRepository.findByIdOrNull(orderfdId)
            ?: throw IllegalArgumentException("User dengan ID $orderfdId tidak ditemukan")

        val request = CreateOrderfdRequest(
            waktumulai = waktumulai,
            waktuselesai = waktuselesai,
            foto_onefd = "",
            foto_twofd = "",
            foto_threefd = "",
            foto_fourfd = "",
            keterangan = keteranganfd ?: "",
            order = orderT.id_order!!
        )

        val orderdfdResponse = orderfdService.create(request, foto_onefd, foto_twofd, foto_threefd, foto_fourfd, orderT) // , products, uprofile
        return WebResponse(
            code = 200,
            status = "OK",
            data = orderdfdResponse
        )
    }

    @GetMapping(
        value = ["/api/ordersfd/{idOrderfd}"],
        produces = ["application/json"]
    )
    fun getOrderfd(@PathVariable("idOrderfd") id: Long): WebResponse<OrderfdResponse> {
        val orderdfdResponse = orderfdService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = orderdfdResponse
        )
    }

    @PutMapping(
        value = ["/api/ordersfd/{idOrderfd}"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    fun updateOrderfd(
        @PathVariable("idOrderfd") id: Long,
        @RequestParam("waktumulai") waktumulai: String,
        @RequestParam("waktuselesai") waktuselesai: String,
        @RequestParam("keteranganfd") keteranganfd: String?,
        @RequestParam("foto_onefd", required = false) foto_onefd: MultipartFile?, // Ubah ke opsional
        @RequestParam("foto_twofd", required = false) foto_twofd: MultipartFile?, // Ubah ke opsional
        @RequestParam("foto_threefd", required = false) foto_threefd: MultipartFile?, // Ubah ke opsional
        @RequestParam("foto_fourfd", required = false) foto_fourfd: MultipartFile?, // Ubah ke opsional
    ): WebResponse<OrderfdResponse> {
        val request = UpdateOrderfdRequest(
            waktumulai = waktumulai,
            waktuselesai = waktuselesai,
            keterangan = keteranganfd ?: "",
            foto_onefd = "",
            foto_twofd = "",
            foto_threefd = "",
            foto_fourfd = ""
        )
        val orderfdResponse = orderfdService.update(id, request, foto_onefd, foto_twofd, foto_threefd, foto_fourfd)
        return WebResponse(
            code = 200,
            status = "OK",
            data = orderfdResponse
        )
    }

    @DeleteMapping(
        value = ["/api/ordersfd/{idOrderfd}"],
        produces = ["application/json"]
    )
    fun deleteOrderfd(@PathVariable("idOrderfd") id: Long): WebResponse<String>{
        val hapus =  orderfdService.delete(id).toString()
        return WebResponse(
            code = 200,
            status = "OK",
            data = hapus
        )
    }

    @GetMapping(
        value = ["/api/ordersfd"],
        produces = ["application/json"]
    )
    fun listOrdersfd(@RequestParam(value = "size", defaultValue = "10") size:Int,
                   @RequestParam(value = "page", defaultValue = "0")page:Int): WebResponse<List<OrderfdResponse>> {
        val request = ListOrderfdRequest(page = page, size = size)
        val responses = orderfdService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )

    }



}
