package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Brand
import programmer_zaman_now.kotlin_restful_api.entity.orders.Promo

interface PromoRepository: JpaRepository<Promo, Long> {

    fun findByIdpromo(idPromo: Long): Promo?

    @Query("SELECT c FROM Promo c WHERE LOWER(c.namapromo) = LOWER(:namapromo)")
    fun findByNamaPromo(@Param("namapromo") namapromo: String): Promo?

}