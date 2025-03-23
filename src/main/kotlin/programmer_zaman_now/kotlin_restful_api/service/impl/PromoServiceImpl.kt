package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import programmer_zaman_now.kotlin_restful_api.entity.Category
import programmer_zaman_now.kotlin_restful_api.entity.Orders
import programmer_zaman_now.kotlin_restful_api.entity.orders.Packet
import programmer_zaman_now.kotlin_restful_api.entity.orders.Promo
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.packet.PacketResponse
import programmer_zaman_now.kotlin_restful_api.model.promo.CreatePromoRequest
import programmer_zaman_now.kotlin_restful_api.model.promo.ListPromoRequest
import programmer_zaman_now.kotlin_restful_api.model.promo.PromoResponse
import programmer_zaman_now.kotlin_restful_api.model.promo.UpdatePromoRequest
import programmer_zaman_now.kotlin_restful_api.repository.CategoryRepository
import programmer_zaman_now.kotlin_restful_api.repository.OrderRepository
import programmer_zaman_now.kotlin_restful_api.repository.PromoRepository
import programmer_zaman_now.kotlin_restful_api.service.PromoService
import java.util.Date
import java.util.stream.Collectors

@Service
class PromoServiceImpl (val promoRepository: PromoRepository, val orderRepository: OrderRepository, val categoryRepository: CategoryRepository): PromoService{
    override fun create(createPromoRequest: CreatePromoRequest): PromoResponse {
        val promok = Promo(
            namapromo = createPromoRequest.namapromo!!,
            kethargapromo = createPromoRequest.kethargapromo!!,
            ketpromo = createPromoRequest.ketpromo,
            createdAt = Date(),
            updatedAt = null,
            namakategori = createPromoRequest.namakategori!!,
            exppromo = createPromoRequest.exppromo!!,
            iconpromo = createPromoRequest.iconpromo!!
        )
        promoRepository.save(promok)
        return convertPromoToPromoResponse(promok)
    }

    override fun get(id: Long): PromoResponse {
        val promo = findPromoByOrThrowNotFound(id)
        return convertPromoToPromoResponse(promo)
    }

    override fun getnamapromo(namapromo: String): PromoResponse {
        println("Mencari promo dengan nama: $namapromo") // Debugging
        val promo = findPromoNameByOrThrowNotFound(namapromo)
        println("paket ditemukan: ${promo.namapromo}") // Debugging
        return convertPromoToPromoResponse(promo)
    }

    override fun update(id: Long, updatePromoRequest: UpdatePromoRequest): PromoResponse {
        val promo = findPromoByOrThrowNotFound(id)

        promo.apply {
            namapromo = updatePromoRequest.namapromo!!
            kethargapromo = updatePromoRequest.kethargapromo!!
            ketpromo = updatePromoRequest.ketpromo!!
            updatedAt = Date()
            namakategori = updatePromoRequest.namakategori!!
            exppromo = updatePromoRequest.exppromo!!
            iconpromo = updatePromoRequest.iconpromo!!
        }

        promoRepository.save(promo)

        return convertPromoToPromoResponse(promo)
    }

    override fun delete(id: Long) {
        val promo = findPromoByOrThrowNotFound(id)
        promoRepository.delete(promo)
    }

    override fun list(listPromoRequest: ListPromoRequest): List<PromoResponse> {
        val page = promoRepository.findAll(PageRequest.of(listPromoRequest.page, listPromoRequest.size))
        val promotions: List<Promo> = page.get().collect(Collectors.toList())

        return promotions.map { convertPromoToPromoResponse(it) }
    }

    private fun findPromoByOrThrowNotFound(id: Long): Promo =
        promoRepository.findByIdpromo(id) ?: throw NotFoundExpection()

    private fun findPromoNameByOrThrowNotFound(namapromo: String): Promo {
        return promoRepository.findByNamaPromo(namapromo)
            ?: throw NotFoundExpection()
    }

    private fun convertPromoToPromoResponse(promo: Promo): PromoResponse {
        return PromoResponse(
            idpromo = promo.idpromo!!,
            namapromo = promo.namapromo,
            kethargapromo = promo.kethargapromo,
            ketpromo = promo.ketpromo,
            createdAt = promo.createdAt,
            updatedAt = promo.updatedAt,
            namakategori = promo.namakategori,
            exppromo = promo.exppromo,
            iconpromo = promo.iconpromo
        )
    }
}
