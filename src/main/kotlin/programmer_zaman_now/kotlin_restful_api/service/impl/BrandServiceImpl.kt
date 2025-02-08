package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Brand
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.BrandResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.CreateBrandRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.ListBrandRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.UpdateBrandRequest
import programmer_zaman_now.kotlin_restful_api.repository.BrandRepository
import programmer_zaman_now.kotlin_restful_api.service.BrandService
import java.util.Date
import java.util.stream.Collectors

@Service
class BrandServiceImpl (val brandRepository: BrandRepository): BrandService{
    override fun create(createBrandRequest: CreateBrandRequest): BrandResponse {
        val brand = Brand(
            merekkendaraan = createBrandRequest.merekkendaraan!!,
            createdAt = Date(),
            updatedAt = null
        )
        brandRepository.save(brand)
        return convertBrandToBrandResponse(brand)
    }

    override fun get(id: Long): BrandResponse {
        val brand = findBrandByOrThrowNotFound(id)
        return convertBrandToBrandResponse(brand)
    }

    override fun getmerekknd(merekkendaraan: String): BrandResponse {
        println("Mencari kategori dengan nama: $merekkendaraan") // Debugging
        val brand = findBrandNameByOrThrowNotFound(merekkendaraan)
        println("Kategori ditemukan: ${brand.merekkendaraan}") // Debugging
        return convertBrandToBrandResponse(brand)
    }

    override fun update(id: Long, updateBrandRequest: UpdateBrandRequest): BrandResponse {
        val brand = findBrandByOrThrowNotFound(id)

        brand.apply {
            merekkendaraan = updateBrandRequest.merekkendaraan!!
            updatedAt = Date()
        }

        brandRepository.save(brand)

        return convertBrandToBrandResponse(brand)
    }

    override fun delete(id: Long) {
        val brand = findBrandByOrThrowNotFound(id)
        brandRepository.delete(brand)
    }

    override fun list(listBrandRequest: ListBrandRequest): List<BrandResponse> {
        val page = brandRepository.findAll(PageRequest.of(listBrandRequest.page, listBrandRequest.size))
        val brands: List<Brand> = page.get().collect(Collectors.toList())

        return brands.map { convertBrandToBrandResponse(it) }
    }

    private fun findBrandByOrThrowNotFound(id: Long): Brand =
        brandRepository.findByIdmerek(id) ?: throw NotFoundExpection()

    private fun findBrandNameByOrThrowNotFound(merekkendaraan: String): Brand {
        return brandRepository.findByMerekKendaraan(merekkendaraan)
            ?: throw NotFoundExpection()
    }

    private fun convertBrandToBrandResponse(brand: Brand): BrandResponse {
        return BrandResponse(
            idmerek = brand.idmerek!!,
            merekkendaraan = brand.merekkendaraan,
            createdAt = brand.createdAt,
            updatedAt = brand.updatedAt
        )
    }
}