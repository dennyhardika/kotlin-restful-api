package programmer_zaman_now.kotlin_restful_api.repository


import programmer_zaman_now.kotlin_restful_api.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>{

}