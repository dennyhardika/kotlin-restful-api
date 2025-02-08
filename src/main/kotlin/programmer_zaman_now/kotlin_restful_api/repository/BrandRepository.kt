package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import programmer_zaman_now.kotlin_restful_api.entity.Category
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Brand

interface BrandRepository: JpaRepository<Brand, Long> {
    fun findByIdmerek(idMerek: Long): Brand?

    @Query("SELECT c FROM Brand c WHERE LOWER(c.merekkendaraan) = LOWER(:merekkendaraan)")
    fun findByMerekKendaraan(@Param("merekkendaraan") merekkendaraan: String): Brand?
}