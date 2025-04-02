package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import programmer_zaman_now.kotlin_restful_api.entity.Category
import programmer_zaman_now.kotlin_restful_api.entity.Product
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import programmer_zaman_now.kotlin_restful_api.error.NotFoundException
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.product.CreateProductRequest
import programmer_zaman_now.kotlin_restful_api.model.product.ListProductRequest
import programmer_zaman_now.kotlin_restful_api.model.product.ProductResponse
import programmer_zaman_now.kotlin_restful_api.model.product.UpdateProductRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UprofileResponse
import programmer_zaman_now.kotlin_restful_api.repository.CategoryRepository
import programmer_zaman_now.kotlin_restful_api.repository.ProductRepository
import programmer_zaman_now.kotlin_restful_api.service.ProductService
import java.util.Date
import java.util.stream.Collectors

@Service
class ProductServiceImpl(val productRepository: ProductRepository, val categoryRepository: CategoryRepository): ProductService {
    override fun create(
        createProductRequest: CreateProductRequest, category: Category): ProductResponse {
        val product = Product(
            namaproduk = createProductRequest.namaproduk!!,
            createdAt = Date(),
            updatedAt = null,
            categories = category
        )
        productRepository.save(product)

        return ProductResponse(
            idproduk = product.idproduk!!,
            namaproduk = product.namaproduk!!,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt,
            category = category.idkategori!!
        )
    }

    override fun get(id: Long): ProductResponse {
        val product = findProductByOrThrowNotFound(id)
        return convertProductToProductResponse(product)
    }

    override fun getnamapdk(namaProduk: String): ProductResponse {
        println("Mencari kategori dengan nama: $namaProduk") // Debugging
        val product = findProductNameByOrThrowNotFound(namaProduk)
        println("Kategori ditemukan: ${product.namaproduk}") // Debugging
        return convertProductToProductResponse(product)
    }

    // Metode baru untuk mendapatkan produk berdasarkan kategori
    override fun getProductsByCategory(categoryId: Long): List<ProductResponse> {
        // Ambil semua produk berdasarkan categoryId
        val products = productRepository.findByCategories_Idkategori(categoryId)

        // Mengonversi daftar produk ke daftar response untuk API
        return products.map { convertProductToProductResponse(it) }
    }

    override fun update(id: Long, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = findProductByOrThrowNotFound(id)

        // Ambil group berdasarkan idGrup dari request
        val ktg = categoryRepository.findById(updateProductRequest.idkategori)
            .orElseThrow { throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Group tidak ditemukan") }


        product.apply {
            namaproduk = updateProductRequest.namaproduk ?: namaproduk
            updatedAt = Date()
            this.categories = ktg
        }

        productRepository.save(product)

        return convertProductToProductResponse(product)
    }

    override fun delete(id: Long) {
        val product = findProductByOrThrowNotFound(id)
        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val products: List<Product> = page.get().collect(Collectors.toList())

        return products.map { convertProductToProductResponse(it) }
    }

    private fun findProductByOrThrowNotFound(id: Long): Product {
        val product = productRepository.findByIdOrNull(id)
        if (product == null){
            throw NotFoundExpection()
        }else {
            return product;
        }
    }

    private fun findProductNameByOrThrowNotFound(namaproduk: String): Product {
        return productRepository.findByNamaproduk(namaproduk)
            ?: throw NotFoundExpection()
    }

    private fun convertProductToProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            idproduk = product.idproduk!!,
            namaproduk = product.namaproduk,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt,
            category = product.categories?.idkategori ?: throw IllegalStateException(" Category is null in Product")
        )
    }
}
