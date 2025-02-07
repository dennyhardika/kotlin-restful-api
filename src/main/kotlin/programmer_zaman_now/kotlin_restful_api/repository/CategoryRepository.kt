package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import programmer_zaman_now.kotlin_restful_api.entity.Category

interface CategoryRepository: JpaRepository<Category, Long> {
  fun findByIdkategori(idKategori: Long): Category?
  
  @Query("SELECT c FROM Category c WHERE LOWER(c.namakategori) = LOWER(:namakategori)")
  fun findByNamakategori(@Param("namakategori") namakategori: String): Category?
}
