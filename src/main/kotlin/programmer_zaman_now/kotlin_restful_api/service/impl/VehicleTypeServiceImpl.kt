package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import programmer_zaman_now.kotlin_restful_api.entity.Product
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Brand
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Group
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.VehicleType
import programmer_zaman_now.kotlin_restful_api.error.NotFoundException
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.CreateTypeRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.ListTypeRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.TypeResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.UpdateTypeRequest
import programmer_zaman_now.kotlin_restful_api.model.product.ProductResponse
import programmer_zaman_now.kotlin_restful_api.repository.BrandRepository
import programmer_zaman_now.kotlin_restful_api.repository.GroupRepository
import programmer_zaman_now.kotlin_restful_api.repository.TypeRepository
import programmer_zaman_now.kotlin_restful_api.service.VehicleTypeService
import java.util.Date
import java.util.stream.Collectors

@Service
class VehicleTypeServiceImpl(val typeRepository: TypeRepository, val brandRepository: BrandRepository, val groupRepository: GroupRepository): VehicleTypeService{
    override fun create(createTypeRequest: CreateTypeRequest, brand: Brand, group: Group): TypeResponse {
        val vehicletype = VehicleType(
            tipekendaraan = createTypeRequest.tipekendaraan!!,
            createdAt = Date(),
            updatedAt = null,
            brands = brand,
            group = group
        )
        typeRepository.save(vehicletype)

        return TypeResponse(
            idtipe = vehicletype.idtipe!!,
            tipekendaraan = vehicletype.tipekendaraan!!,
            createdAt = vehicletype.createdAt,
            updatedAt = vehicletype.updatedAt,
            brand = brand.idmerek!!,
            group = group.idgrup!!
        )
    }

    override fun get(id: Long): TypeResponse {
        val vehicletype = findVehicleTypeByOrThrowNotFound(id)
        return convertVehicleTypeToVehicleTypeResponse(vehicletype)
    }

    override fun gettipeknd(tipekendaraan: String): TypeResponse {
        println("Mencari kategori dengan nama: $tipekendaraan") // Debugging
        val product = findVehicleTypeNameByOrThrowNotFound(tipekendaraan)
        println("Kategori ditemukan: ${product.tipekendaraan}") // Debugging
        return convertVehicleTypeToVehicleTypeResponse(product)
    }

    override fun update(id: Long, updateTypeRequest: UpdateTypeRequest): TypeResponse {
        val vehicletype = findVehicleTypeByOrThrowNotFound(id)

        // Ambil kategori jika categoryId diberikan
        val brand = updateTypeRequest.idmerek?.let {
            brandRepository.findById(it).orElseThrow { NotFoundException("Brand not found") }
        }

        vehicletype.apply {
            tipekendaraan = updateTypeRequest.tipekendaraan ?: tipekendaraan
            updatedAt = Date()
            if (brand != null) brands = brand
        }

        typeRepository.save(vehicletype)

        return convertVehicleTypeToVehicleTypeResponse(vehicletype)
    }

    override fun delete(id: Long) {
        val vehicletype = findVehicleTypeByOrThrowNotFound(id)
        typeRepository.delete(vehicletype)
    }

    override fun list(listTypeRequest: ListTypeRequest): List<TypeResponse> {
        val page = typeRepository.findAll(PageRequest.of(listTypeRequest.page, listTypeRequest.size))
        val types: List<VehicleType> = page.get().collect(Collectors.toList())

        return types.map { convertVehicleTypeToVehicleTypeResponse(it) }
    }

    private fun findVehicleTypeByOrThrowNotFound(id: Long): VehicleType {
        return typeRepository.findByIdOrNull(id) ?: throw NotFoundException("Vehicle Type with id $id not found")
    }

    private fun findVehicleTypeNameByOrThrowNotFound(tipekendaraan: String): VehicleType {
        return typeRepository.findByTipeKendaraan(tipekendaraan)
            ?: throw NotFoundExpection()
    }

    private fun convertVehicleTypeToVehicleTypeResponse(vehicleType: VehicleType): TypeResponse {
        return TypeResponse(
            idtipe =  vehicleType.idtipe!!,
            tipekendaraan = vehicleType.tipekendaraan,
            createdAt = vehicleType.createdAt,
            updatedAt = vehicleType.updatedAt,
            brand = vehicleType.brands?.idmerek ?: throw IllegalStateException(" Brand is null in Vehicle Type"),
            group = vehicleType.group?.idgrup ?: throw IllegalStateException(" Group is null in Vehicle Type")
        )
    }
}