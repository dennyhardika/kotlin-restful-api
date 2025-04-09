package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import programmer_zaman_now.kotlin_restful_api.entity.Product
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile

interface UprofileRepository: JpaRepository<Uprofile, Long> {

    @Query("SELECT c FROM Uprofile c WHERE LOWER(c.namalengkap) = LOWER(:namalengkap)")
    fun findByNamalengkap(@Param("namalengkap") namalengkap: String): Uprofile?

    // Query untuk mencari produk berdasarkan ID kategori
    fun findByUsers_Iduser(userId: Long): List<Uprofile>

}
