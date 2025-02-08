package programmer_zaman_now.kotlin_restful_api.repository


import programmer_zaman_now.kotlin_restful_api.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import programmer_zaman_now.kotlin_restful_api.entity.Category

interface ProductRepository : JpaRepository<Product, Long>{
    fun findByIdproduk(idProduk: Long): Product?

    @Query("SELECT c FROM Product c WHERE LOWER(c.namaproduk) = LOWER(:namaproduk)")
    fun findByNamaproduk(@Param("namaproduk") namaproduk: String): Product?
}