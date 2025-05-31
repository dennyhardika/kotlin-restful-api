package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import programmer_zaman_now.kotlin_restful_api.entity.Orders
import programmer_zaman_now.kotlin_restful_api.entity.orders.Ordersfd
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.order.OrderResponse
import programmer_zaman_now.kotlin_restful_api.model.orderfd.CreateOrderfdRequest
import programmer_zaman_now.kotlin_restful_api.model.orderfd.ListOrderfdRequest
import programmer_zaman_now.kotlin_restful_api.model.orderfd.OrderfdResponse
import programmer_zaman_now.kotlin_restful_api.model.orderfd.UpdateOrderfdRequest
import programmer_zaman_now.kotlin_restful_api.model.product.ProductResponse
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UprofileResponse
import programmer_zaman_now.kotlin_restful_api.repository.OrderRepository
import programmer_zaman_now.kotlin_restful_api.repository.OrderfdRepository
import programmer_zaman_now.kotlin_restful_api.service.FileStorageService
import programmer_zaman_now.kotlin_restful_api.service.OrderfdService
import java.util.Date
import java.util.stream.Collectors

@Service
class OrderfdServiceImpl(val orderfdRepository: OrderfdRepository, val orderRepository: OrderRepository, val fileStorageService: FileStorageService): OrderfdService {
    override fun create(
        createOrderfdRequest: CreateOrderfdRequest,
        fotoOnefd: MultipartFile?,
        fotoTwofd: MultipartFile?,
        fotoThreefd: MultipartFile?,
        fotoFourfd: MultipartFile?,
        orders: Orders
    ): OrderfdResponse {
        println("Create Order Request: $createOrderfdRequest")
        val fotoOnePath = fotoOnefd?.let { fileStorageService.saveFile(it) } ?: ""
        val fotoTwoPath = fotoTwofd?.let { fileStorageService.saveFile(it) } ?: ""
        val fotoThreePath = fotoThreefd?.let { fileStorageService.saveFile(it) } ?: ""
        val fotoFourPath = fotoFourfd?.let { fileStorageService.saveFile(it) } ?: ""

        if (createOrderfdRequest.order == null) {
            throw IllegalArgumentException("order tidak boleh null")
        }

        val ordersfd = Ordersfd(
            waktumulai = createOrderfdRequest.waktumulai,
            waktuselesai = createOrderfdRequest.waktuselesai ?: "",
            foto_onefd = fotoOnePath,
            foto_twofd = fotoTwoPath,
            foto_threefd = fotoThreePath,
            foto_fourfd = fotoFourPath,
            keteranganfd = createOrderfdRequest.keterangan ?: "",
            orders = orders,
            createdAt = Date(),
            updatedAt = null,
        )
        orderfdRepository.save(ordersfd)

        return convertOrderfdToOrderfdResponse(ordersfd)
    }

    override fun get(id: Long): OrderfdResponse {
        val orderfd = findOrderfdByOrThrowNotFound(id)
        return convertOrderfdToOrderfdResponse(orderfd)
    }

    // Metode baru untuk mendapatkan produk berdasarkan kategori
    override fun getOrdersfdByOrder(orderId: Long): List<OrderfdResponse> {
        // Ambil semua produk berdasarkan categoryId
        val orderfd = orderfdRepository.findByOrders_Idorder(orderId)

        // Mengonversi daftar produk ke daftar response untuk API
        return orderfd.map { convertOrderfdToOrderfdResponse(it) }
    }

    override fun update(
        id: Long,
        updateOrderfdRequest: UpdateOrderfdRequest,
        fotoOnefd: MultipartFile?,
        fotoTwofd: MultipartFile?,
        fotoThreefd: MultipartFile?,
        fotoFourfd: MultipartFile?
    ): OrderfdResponse {
        val orderfd = findOrderfdByOrThrowNotFound(id)

        orderfd.apply {
            waktumulai = updateOrderfdRequest.waktumulai
            waktuselesai = updateOrderfdRequest.waktuselesai ?: ""
            keteranganfd = updateOrderfdRequest.keterangan ?: ""
            updatedAt = Date()

            // Hapus & ganti foto profil jika ada
            if (fotoOnefd != null) {
            if (!foto_onefd.isNullOrEmpty()) fileStorageService.deleteFile(foto_onefd!!)
            foto_onefd = fileStorageService.saveFile(fotoOnefd)
        }
            if (fotoTwofd != null) {
                if (!foto_twofd.isNullOrEmpty()) fileStorageService.deleteFile(foto_twofd!!)
                foto_twofd = fileStorageService.saveFile(fotoTwofd)
            }
            if (fotoThreefd != null) {
                if (!foto_threefd.isNullOrEmpty()) fileStorageService.deleteFile(foto_threefd!!)
                foto_threefd = fileStorageService.saveFile(fotoThreefd)
            }
            if (fotoFourfd != null) {
                if (!foto_fourfd.isNullOrEmpty()) fileStorageService.deleteFile(foto_fourfd!!)
                foto_fourfd = fileStorageService.saveFile(fotoFourfd)
            }
        }
        orderfdRepository.save(orderfd)

        return convertOrderfdToOrderfdResponse(orderfd)
    }

    override fun delete(id: Long) {
        val orderfd = findOrderfdByOrThrowNotFound(id)
        orderfdRepository.delete(orderfd)
    }

    override fun list(listOrderfdRequest: ListOrderfdRequest): List<OrderfdResponse> {
        val page = orderfdRepository.findAll(PageRequest.of(listOrderfdRequest.page, listOrderfdRequest.size))
        val ordersfd: MutableList<Ordersfd>? = page.get().collect(Collectors.toList())

        return ordersfd!!.map { convertOrderfdToOrderfdResponse(it) }
    }

    private fun findOrderfdByOrThrowNotFound(id: Long): Ordersfd {
        val orderfd = orderfdRepository.findByIdOrNull(id)
        if (orderfd == null){
            throw NotFoundExpection()
        }else {
            return orderfd;
        }
    }

    private fun convertOrderfdToOrderfdResponse(ordersfd: Ordersfd): OrderfdResponse {
        return OrderfdResponse(
            id_orderfd = ordersfd.id_orderfd!!,
            waktumulai = ordersfd.waktumulai!!,
            waktuselesai = ordersfd.waktuselesai!!,
            keterangan = ordersfd.keteranganfd ?: "",
            foto_onefd = ordersfd.foto_onefd ?: "",
            foto_twofd = ordersfd.foto_twofd ?: "",
            foto_threefd = ordersfd.foto_threefd ?: "",
            foto_fourfd = ordersfd.foto_fourfd ?: "",
            order = ordersfd.orders?.id_order ?: throw IllegalStateException("Order is null in Orderfd"),
            createdAt = ordersfd.createdAt,
            updatedAt = ordersfd.updatedAt,
        )
    }
}
