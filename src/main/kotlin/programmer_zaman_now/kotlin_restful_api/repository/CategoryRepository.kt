package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import programmer_zaman_now.kotlin_restful_api.entity.Category

interface CategoryRepository: JpaRepository<Category, Long> {
  fun findByIdkategori(idKategori: Long): Category?
}
