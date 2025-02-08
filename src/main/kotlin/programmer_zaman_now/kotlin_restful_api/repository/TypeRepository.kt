package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.VehicleType

interface TypeRepository: JpaRepository<VehicleType, Long> {
    fun findByIdtipe(idtipe: Long): VehicleType?

    @Query("SELECT c FROM VehicleType c WHERE LOWER(c.tipekendaraan) = LOWER(:tipekendaraan)")
    fun findByTipeKendaraan(@Param("tipekendaraan") tipekendaraan: String): VehicleType?
}