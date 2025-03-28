package programmer_zaman_now.kotlin_restful_api.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import programmer_zaman_now.kotlin_restful_api.model.WebResponse
import programmer_zaman_now.kotlin_restful_api.model.order.CreateOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.ListOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.OrderResponse
import programmer_zaman_now.kotlin_restful_api.model.order.UpdateOrderRequest
import programmer_zaman_now.kotlin_restful_api.repository.ProductRepository
import programmer_zaman_now.kotlin_restful_api.repository.UprofileRepository
import programmer_zaman_now.kotlin_restful_api.service.FileStorageService
import programmer_zaman_now.kotlin_restful_api.service.OrderService

@RestController
class OrderController(val orderService: OrderService, val productRepository: ProductRepository, val uprofileRepository: UprofileRepository, val fileStorageService: FileStorageService) {

    @Throws(IllegalArgumentException::class)
    @PostMapping(
        value = ["/api/orders"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"] // Ubah menjadi multipart
    )
    fun createOrder(
        @RequestParam("iconorder") iconorder: String,
        @RequestParam("kategori1") kategori1: String,
        @RequestParam("produk1a") produk1a: String,
        @RequestParam("produk1b") produk1b: String,
        @RequestParam("produk1c") produk1c: String,
        @RequestParam("produk1d") produk1d: String,
        @RequestParam("kategori2") kategori2: String,
        @RequestParam("produk2a") produk2a: String,
        @RequestParam("produk2b") produk2b: String,
        @RequestParam("produk2c") produk2c: String,
        @RequestParam("produk2d") produk2d: String,
        @RequestParam("startedAt") startedAt: String,
        @RequestParam("finishedAt") finishedAt: String,
        @RequestParam("tanggalkedatangan") tanggalkedatangan: String,
        @RequestParam("keterangan") keterangan: String?,
        @RequestParam("foto_one", required = false) foto_one: MultipartFile?, // Ubah ke opsional
        @RequestParam("foto_two", required = false) foto_two: MultipartFile?, // Ubah ke opsional
        @RequestParam("statusbooking") statusbooking: String,
        @RequestParam("tipebooking") tipebooking: String,
        @RequestParam("namabooking") namabooking: String,
        @RequestParam("uprofile") uprofileId: Long
//        @RequestParam("packetIds") packetIds: List<Long>?, // Tambahkan daftar ID paket
//        @RequestParam("promoIds") promoIds: List<Long>? // Tambahkan daftar ID promo

    ): WebResponse<OrderResponse> {

        val uprofile = uprofileRepository.findByIdOrNull(uprofileId)
            ?: throw IllegalArgumentException("User dengan ID $uprofileId tidak ditemukan")

        val request = CreateOrderRequest(
            iconorder = iconorder,
            kategori1 = kategori1,
            produk1a =  produk1a,
            produk1b = produk1b,
            produk1c = produk1c,
            produk1d = produk1d,
            kategori2 = kategori2,
            produk2a =  produk2a,
            produk2b = produk2b,
            produk2c = produk2c,
            produk2d = produk2d,
            startedAt = startedAt,
            finishedAt = finishedAt,
            tanggalkedatangan = tanggalkedatangan,
            keterangan = keterangan ?: "",  // Default kosong jika null
            foto_one = "",
            foto_two = "",
            statusbooking = statusbooking,
            tipebooking = tipebooking,
            namabooking = namabooking,
            uprofile = uprofile.iduprofile!!
//            packetIds = packetIds ?: emptyList(), // Default kosong jika null
//            promoIds = promoIds ?: emptyList() // Default kosong jika null
        )

        val orderResponse = orderService.create(request, foto_one, foto_two, uprofile) // , products, uprofile
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
        consumes = ["multipart/form-data"]
    )
    fun updateOrder(
        @PathVariable("idOrder") id: Long,
        @RequestParam("iconorder") iconorder: String,
        @RequestParam("startedAt") startedAt: String,
        @RequestParam("finishedAt") finishedAt: String,
        @RequestParam("tanggalkedatangan") tanggalkedatangan: String,
        @RequestParam("keterangan") keterangan: String?,
        @RequestParam("foto_one", required = false) foto_one: MultipartFile?, // Ubah ke opsional
        @RequestParam("foto_two", required = false) foto_two: MultipartFile?, // Ubah ke opsional
        @RequestParam("statusbooking") statusbooking: String,
        @RequestParam("tipebooking") tipebooking: String,
        @RequestParam("namabooking") namabooking: String,
        @RequestParam("kategori1") kategori1: String,
        @RequestParam("produk1a") produk1a: String,
        @RequestParam("produk1b") produk1b: String,
        @RequestParam("produk1c") produk1c: String,
        @RequestParam("produk1d") produk1d: String,
        @RequestParam("kategori2") kategori2: String,
        @RequestParam("produk2a") produk2a: String,
        @RequestParam("produk2b") produk2b: String,
        @RequestParam("produk2c") produk2c: String,
        @RequestParam("produk2d") produk2d: String
//        @RequestParam("packetIds") packetIds: List<Long>?, // Tambahkan daftar ID paket
//        @RequestParam("promoIds") promoIds: List<Long>?
    ): WebResponse<OrderResponse> {
        val existingOrder = orderService.get(id)

        // **Hapus file lama sebelum menyimpan yang baru**
        val fotoOnePath = when {
            foto_one != null -> fileStorageService.saveFile(foto_one)
            foto_one == null && existingOrder.foto_one != null -> null // Hapus foto jika dikirim null
            else -> existingOrder.foto_one
        }

        val fotoTwoPath = when {
            foto_two != null -> fileStorageService.saveFile(foto_two)
            foto_two == null && existingOrder.foto_two != null -> null
            else -> existingOrder.foto_two
        }

        val request = UpdateOrderRequest(
            iconorder = iconorder,
            startedAt = startedAt,
            finishedAt = finishedAt,
            tanggalkedatangan = tanggalkedatangan,
            keterangan = keterangan ?: "",
            foto_one = fotoOnePath,
            foto_two = fotoTwoPath,
            statusbooking = statusbooking,
            tipebooking = tipebooking,
            namabooking = namabooking,
            kategori1 = kategori1,
            produk1a =  produk1a,
            produk1b = produk1b,
            produk1c = produk1c,
            produk1d = produk1d,
            kategori2 = kategori2,
            produk2a =  produk1a,
            produk2b = produk2b,
            produk2c = produk2c,
            produk2d = produk2d
//            packetIds = packetIds ?: emptyList(), // Default kosong jika null
//            promoIds = promoIds ?: emptyList() // Default kosong jika null
        )

        val orderResponse = orderService.update(id, request, foto_one, foto_two)
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
