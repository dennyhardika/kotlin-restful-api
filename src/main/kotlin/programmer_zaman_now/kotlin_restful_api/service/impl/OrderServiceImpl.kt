package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import programmer_zaman_now.kotlin_restful_api.entity.Orders
import programmer_zaman_now.kotlin_restful_api.entity.Product
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.order.CreateOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.ListOrderRequest
import programmer_zaman_now.kotlin_restful_api.model.order.OrderResponse
import programmer_zaman_now.kotlin_restful_api.model.order.UpdateOrderRequest
import programmer_zaman_now.kotlin_restful_api.repository.OrderRepository
import programmer_zaman_now.kotlin_restful_api.repository.ProductRepository
import programmer_zaman_now.kotlin_restful_api.repository.UprofileRepository
import programmer_zaman_now.kotlin_restful_api.service.OrderService
import java.util.Date
import java.util.stream.Collectors

@Service
class OrderServiceImpl(val orderRepository: OrderRepository, val productRepository: ProductRepository, val uprofileRepository: UprofileRepository): OrderService {
    override fun create(createOrderRequest: CreateOrderRequest, products: List<Product>, uprofile: Uprofile): OrderResponse {
        val products = productRepository.findAllById(createOrderRequest.productIds!!)

        val order = Orders(
            createdAt = Date(),
            updatedAt = null,
            startedAt = null,
            finishedAt = null,
            keterangan = createOrderRequest.keterangan!!,
            foto_one = createOrderRequest.foto_one!!,
            foto_two = createOrderRequest.foto_two!!,
            status = createOrderRequest.status!!,
            uprofiles = uprofile,
            // Relasikan dengan produk
            products = products.toMutableList() // Menyambungkan produk yang dipilih ke pesanan
        )
        orderRepository.save(order)

        return OrderResponse(
            id_order = order.id_order!!,
            id_uprofile = uprofile.id_uprofile!!,
            productIds = products.map { it.id_produk},
            createdAt = order.createdAt,
            updatedAt = order.updatedAt,
            startedAt = order.startedAt,
            finishedAt = order.finishedAt,
            keterangan = order.keterangan,
            foto_one = order.foto_one,
            foto_two = order.foto_two,
            status = order.status
        )
    }

    override fun get(id: Long): OrderResponse {
        val order = findOrderByOrThrowNotFound(id)
        return convertOrderToOrderResponse(order)
    }

    override fun update(id: Long, updateOrderRequest: UpdateOrderRequest): OrderResponse {
        val order = findOrderByOrThrowNotFound(id)
        // Ambil daftar produk berdasarkan ID dari request
        val products = productRepository.findAllById(updateOrderRequest.productIds!!)

        order.apply {
            startedAt = updateOrderRequest.startedAt
            finishedAt = updateOrderRequest.finishedAt
            updatedAt = Date()
            keterangan = updateOrderRequest.keterangan!!
            foto_one = updateOrderRequest.foto_one!!
            foto_two = updateOrderRequest.foto_two!!
            status = updateOrderRequest.status!!
            this.products.clear()  // Hapus produk lama jika perlu
            this.products.addAll(products) // Tambahkan produk baru
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
            id_uprofile = orders.uprofiles.id_uprofile!!,
            productIds = orders.products.map { it.id_produk},
            createdAt = orders.createdAt,
            updatedAt = orders.updatedAt,
            startedAt = orders.startedAt,
            finishedAt = orders.finishedAt,
            keterangan = orders.keterangan,
            foto_one = orders.foto_one,
            foto_two = orders.foto_two,
            status = orders.status
        )
    }

}