package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import programmer_zaman_now.kotlin_restful_api.entity.Category
import programmer_zaman_now.kotlin_restful_api.entity.Product
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.product.CreateProductRequest
import programmer_zaman_now.kotlin_restful_api.model.product.ListProductRequest
import programmer_zaman_now.kotlin_restful_api.model.product.ProductResponse
import programmer_zaman_now.kotlin_restful_api.model.product.UpdateProductRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UprofileResponse
import programmer_zaman_now.kotlin_restful_api.repository.ProductRepository
import programmer_zaman_now.kotlin_restful_api.service.ProductService
import java.util.Date
import java.util.stream.Collectors

@Service
class ProductServiceImpl(val productRepository: ProductRepository): ProductService {
    override fun create(
        createProductRequest: CreateProductRequest, category: Category): ProductResponse {
//        if (productRepository.findByIdOrNull(category.id_kategori) != null) {
//            throw IllegalArgumentException("User already has a profile")
//        }
        val product = Product(
            nama_produk = createProductRequest.nama_produk!!,
            createdAt = Date(),
            updatedAt = null,
            categories = category
        )
        productRepository.save(product)

        return ProductResponse(
            id_produk = product.id_produk!!,
            nama_produk = product.nama_produk!!,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt,
            category = category.id_kategori!!
        )
    }

    override fun get(id: Long): ProductResponse {
        val product = findProductByOrThrowNotFound(id)
        return convertProductToProductResponse(product)
    }

    override fun update(id: Long, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = findProductByOrThrowNotFound(id)

        product.apply {
            nama_produk = updateProductRequest.nama_produk!!
            updatedAt = Date()
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

    private fun convertProductToProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            id_produk = product.id_produk!!,
            nama_produk = product.nama_produk,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt,
            category = product.categories?.id_kategori ?: throw IllegalStateException(" Category is null in Product")
        )
    }
}