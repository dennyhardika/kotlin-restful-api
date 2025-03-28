package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import programmer_zaman_now.kotlin_restful_api.entity.Orders
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.order.CreateOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.ListOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.OrderResponse
import programmer_zaman_now.kotlin_restful_api.model.order.UpdateOrderRequest
import programmer_zaman_now.kotlin_restful_api.repository.OrderRepository
import programmer_zaman_now.kotlin_restful_api.repository.PacketRepository
import programmer_zaman_now.kotlin_restful_api.repository.PromoRepository
import programmer_zaman_now.kotlin_restful_api.repository.UprofileRepository
import programmer_zaman_now.kotlin_restful_api.service.FileStorageService
import programmer_zaman_now.kotlin_restful_api.service.OrderService
import java.util.Date
import java.util.stream.Collectors

@Service
class OrderServiceImpl(val orderRepository: OrderRepository, val uprofileRepository: UprofileRepository, val fileStorageService: FileStorageService, val packetRepository: PacketRepository, val promoRepository: PromoRepository): OrderService {
    override fun create(createOrderRequest: CreateOrderRequest, fotoOne: MultipartFile?, fotoTwo: MultipartFile?, uprofile: Uprofile): OrderResponse { //, products: List<Product>, uprofile: Uprofile
        println("Create Order Request: $createOrderRequest")  // Tambahkan log

        // Ambil paket dan promo, jika kosong gunakan list kosong
//        val packetss = if (!createOrderRequest.packetIds.isNullOrEmpty()) packetRepository.findAllById(createOrderRequest.packetIds) else emptyList()
//        val promoss = if (!createOrderRequest.promoIds.isNullOrEmpty()) promoRepository.findAllById(createOrderRequest.promoIds) else emptyList()

//        val fotoOnePath = fotoOne?.takeIf { !it.isEmpty }?.let { fileStorageService.saveFile(it) }
//        val fotoTwoPath = fotoTwo?.takeIf { !it.isEmpty }?.let { fileStorageService.saveFile(it) }

        val fotoOnePath = fotoOne?.let { fileStorageService.saveFile(it) } ?: ""
        val fotoTwoPath = fotoTwo?.let { fileStorageService.saveFile(it) } ?: ""

        // **Ambil `namapromo` dari promo pertama jika ada, jika tidak gunakan default "Regular"**
        // 🚀 Ambil `namapromo` atau `namapacket` jika ada, prioritas promo lebih dulu
//        val tipeBookingValue = promoss.firstOrNull()?.namapromo ?: packetss.firstOrNull()?.namapaket ?: "Regular"


        val order = Orders(
            kategori1 = createOrderRequest.kategori1,
            produk1a = createOrderRequest.produk1a,
            produk1b = createOrderRequest.produk1b ?: "",
            produk1c = createOrderRequest.produk1c ?: "",
            produk1d = createOrderRequest.produk1d ?: "",
            kategori2 = createOrderRequest.kategori2 ?: "",
            produk2a = createOrderRequest.produk2a ?: "",
            produk2b = createOrderRequest.produk2b ?: "",
            produk2c = createOrderRequest.produk2c ?: "",
            produk2d = createOrderRequest.produk2d ?: "",
            startedAt = createOrderRequest.startedAt,
            finishedAt = createOrderRequest.finishedAt,
            tanggalkedatangan = createOrderRequest.tanggalkedatangan,
            keterangan = createOrderRequest.keterangan ?: "",
            foto_one = fotoOnePath,
            foto_two = fotoTwoPath,
            statusbooking = createOrderRequest.statusbooking,
            tipebooking = createOrderRequest.tipebooking,
            namabooking = createOrderRequest.namabooking,
            uprofiles = uprofile,
            createdAt = Date(),
            updatedAt = null,
//            packets = packetss.toMutableList(),
//            promotions = promoss.toMutableList()
        )
        orderRepository.save(order)

        return convertOrderToOrderResponse(order)
    }

    override fun get(id: Long): OrderResponse {
        val order = findOrderByOrThrowNotFound(id)
        return convertOrderToOrderResponse(order)
    }

    override fun update(id: Long, updateOrderRequest: UpdateOrderRequest, fotoOne: MultipartFile?, fotoTwo: MultipartFile?): OrderResponse {
        val order = findOrderByOrThrowNotFound(id)

        // Ambil paket dan promo, jika kosong biarkan tetap list kosong
//        val packetss = if (!updateOrderRequest.packetIds.isNullOrEmpty()) packetRepository.findAllById(updateOrderRequest.packetIds) else emptyList()
//        val promoss = if (!updateOrderRequest.promoIds.isNullOrEmpty()) promoRepository.findAllById(updateOrderRequest.promoIds) else emptyList()

        // 🚀 Ambil `namapromo` atau `namapacket` jika ada, prioritas promo lebih dulu
//        val tipeBookingValue = promoss.firstOrNull()?.namapromo ?: packetss.firstOrNull()?.namapaket ?: "Regular"


        order.apply {
            updatedAt = Date()
            startedAt = updateOrderRequest.startedAt
            finishedAt = updateOrderRequest.finishedAt
            tanggalkedatangan = updateOrderRequest.tanggalkedatangan
            keterangan = updateOrderRequest.keterangan ?: ""
            statusbooking = updateOrderRequest.statusbooking
            tipebooking = updateOrderRequest.tipebooking
            namabooking = updateOrderRequest.namabooking
            kategori1 = updateOrderRequest.kategori1
            produk1a = updateOrderRequest.produk1a
            produk1b = updateOrderRequest.produk1b ?: ""
            produk1c = updateOrderRequest.produk1c ?: ""
            produk1d = updateOrderRequest.produk1d ?: ""
            kategori2 = updateOrderRequest.kategori2 ?: ""
            produk2a = updateOrderRequest.produk2a ?: ""
            produk2b = updateOrderRequest.produk2b ?: ""
            produk2c = updateOrderRequest.produk2c ?: ""
            produk2d = updateOrderRequest.produk2d ?: ""

            // Update foto jika diberikan, jika tidak biarkan tetap
            if (fotoOne != null) {
                foto_one?.let { fileStorageService.deleteFile(it) }
                foto_one = fileStorageService.saveFile(fotoOne)
            } else if (updateOrderRequest.foto_one == null) {
                foto_one = null
            }

            if (fotoTwo != null) {
                foto_two?.let { fileStorageService.deleteFile(it) }
                foto_two = fileStorageService.saveFile(fotoTwo)
            } else if (updateOrderRequest.foto_two == null) {
                foto_two = null
            }

            // Update daftar paket & promo
//            packets.clear()
//            packets.addAll(packetss)
//            promotions.clear()
//            promotions.addAll(promoss)
        }
        orderRepository.save(order)

        return convertOrderToOrderResponse(order)
    }

    override fun delete(id: Long) {
        val order = findOrderByOrThrowNotFound(id)
        orderRepository.delete(order)
    }

    override fun list(listOrderRequest: ListOrderRequest): List<OrderResponse> {
        val page = orderRepository.findAll(PageRequest.of(listOrderRequest.page, listOrderRequest.size))
        val orders: MutableList<Orders>? = page.get().collect(Collectors.toList())

        return orders!!.map { convertOrderToOrderResponse(it) }
    }

    private fun findOrderByOrThrowNotFound(id: Long): Orders {
        val order = orderRepository.findByIdOrNull(id)
        if (order == null){
            throw NotFoundExpection()
        }else {
            return order;
        }
    }

    private fun convertOrderToOrderResponse(orders: Orders): OrderResponse {
        return OrderResponse(
            id_order = orders.id_order!!,
//            packetIds = orders.packets.map { it.idpaket!! }, // Jika kosong, akan jadi `emptyList()`
//            promoIds = orders.promotions.map { it.idpromo!! }, // Jika kosong, akan jadi `emptyList()`
            kategori1 = orders.kategori1,
            produk1a = orders.produk1a,
            produk1b = orders.produk1b ?: "",
            produk1c = orders.produk1c ?: "",
            produk1d = orders.produk1d ?: "",
            kategori2 = orders.kategori2 ?: "",
            produk2a = orders.produk2a ?: "",
            produk2b = orders.produk2b ?: "",
            produk2c = orders.produk2c ?: "",
            produk2d = orders.produk2d ?: "",
            startedAt = orders.startedAt,
            finishedAt = orders.finishedAt,
            tanggalkedatangan = orders.tanggalkedatangan!!,
            keterangan = orders.keterangan ?: "",
            foto_one = orders.foto_one ?: "",
            foto_two = orders.foto_two ?: "",
            statusbooking = orders.statusbooking,
            tipebooking = orders.tipebooking,
            namabooking = orders.namabooking,
            uprofile = orders.uprofiles?.iduprofile ?: throw IllegalStateException("User is null in Uprofile"),
            createdAt = orders.createdAt,
            updatedAt = orders.updatedAt,
        )
    }

}
