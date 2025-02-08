package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import programmer_zaman_now.kotlin_restful_api.entity.orders.Packet
import programmer_zaman_now.kotlin_restful_api.entity.orders.Promo

interface PacketRepository: JpaRepository<Packet, Long> {

    fun findByIdpaket(idPaket: Long): Packet?

    @Query("SELECT c FROM Packet c WHERE LOWER(c.namapaket) = LOWER(:namapaket)")
    fun findByNamaPaket(@Param("namapaket") namapaket: String): Packet?

}