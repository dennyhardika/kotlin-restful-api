package programmer_zaman_now.kotlin_restful_api.service

import programmer_zaman_now.kotlin_restful_api.entity.Category
import programmer_zaman_now.kotlin_restful_api.model.packet.UpdatePacketRequest
import programmer_zaman_now.kotlin_restful_api.model.promo.CreatePromoRequest
import programmer_zaman_now.kotlin_restful_api.model.promo.ListPromoRequest
import programmer_zaman_now.kotlin_restful_api.model.promo.PromoResponse
import programmer_zaman_now.kotlin_restful_api.model.promo.UpdatePromoRequest

interface PromoService {

    fun create(createPromoRequest: CreatePromoRequest): PromoResponse

    fun get(id: Long): PromoResponse

    fun getnamapromo(namapromo: String): PromoResponse

    fun update(id: Long, updatePromoRequest: UpdatePromoRequest): PromoResponse

    fun delete(id: Long)

    fun list(listPromoRequest: ListPromoRequest): List<PromoResponse>

}