package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Brand
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Group

interface GroupRepository:JpaRepository<Group, Long> {

//    fun findByIdgroup(idGroup: Long): Group?

    @Query("SELECT c FROM Group c WHERE LOWER(c.grupkendaraan) = LOWER(:grupkendaraan)")
    fun findByGrupKendaraan(@Param("grupkendaraan") grupkendaraan: String): Group?

}