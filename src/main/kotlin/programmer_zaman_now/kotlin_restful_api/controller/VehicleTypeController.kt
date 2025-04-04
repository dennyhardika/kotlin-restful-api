package programmer_zaman_now.kotlin_restful_api.controller

import jakarta.validation.Valid
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import programmer_zaman_now.kotlin_restful_api.model.WebResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.CreateTypeRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.ListTypeRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.TypeResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.UpdateTypeRequest
import programmer_zaman_now.kotlin_restful_api.model.product.CreateProductRequest
import programmer_zaman_now.kotlin_restful_api.model.product.ListProductRequest
import programmer_zaman_now.kotlin_restful_api.model.product.ProductResponse
import programmer_zaman_now.kotlin_restful_api.model.product.UpdateProductRequest
import programmer_zaman_now.kotlin_restful_api.repository.BrandRepository
import programmer_zaman_now.kotlin_restful_api.repository.GroupRepository
import programmer_zaman_now.kotlin_restful_api.repository.TypeRepository
import programmer_zaman_now.kotlin_restful_api.service.BrandService
import programmer_zaman_now.kotlin_restful_api.service.VehicleTypeService

@RestController
class VehicleTypeController (val vehicleTypeService: VehicleTypeService, val brandService: BrandService, val brandRepository: BrandRepository, val groupRepository:GroupRepository){

    @PostMapping(
        value = ["/api/types"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createVehicleType(@Valid @RequestBody body: CreateTypeRequest): WebResponse<TypeResponse> {
        val brand = brandRepository.findByIdOrNull(body.brand)
            ?: throw IllegalArgumentException("Brand dengan ID ${body.brand} tidak ditemukan")
        val grup = groupRepository.findByIdOrNull(body.idgrup)
            ?: throw IllegalArgumentException("Group dengan ID ${body.idgrup} tidak ditemukan")

        val vehicletypeResponse = vehicleTypeService.create(body, brand, grup)
        return WebResponse(
            code = 200,
            status = "OK",
            data = vehicletypeResponse
        )
    }

    @GetMapping(
        value = ["/api/types/name/{tipeknd}"],
        produces = ["application/json"]
    )
    fun getVehicleTypeName(@PathVariable("tipeknd") tipeknd: String): WebResponse<TypeResponse> {
        val vehicletypeResponse = vehicleTypeService.gettipeknd(tipeknd)
        return WebResponse(
            code = 200,
            status = "OK",
            data = vehicletypeResponse
        )
    }

    @GetMapping(
        value = ["/api/types/id/{idtipe}"],
        produces = ["application/json"]
    )
    fun getVehicleType(@PathVariable("idtipe") id: Long): WebResponse<TypeResponse> {
        val vehicletypeResponse = vehicleTypeService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = vehicletypeResponse
        )
    }

    // Endpoint baru untuk mendapatkan type berdasarkan brand
    @GetMapping(
        value = ["/api/types/brd/{idmerek}"],
        produces = ["application/json"]
    )
    fun getTypesByBrand(@PathVariable ("idmerek") id: Long): ResponseEntity<List<TypeResponse>> {
        val vehicletypeResponse = vehicleTypeService.getVehicleTypesByBrand(id)
        return ResponseEntity.ok(vehicletypeResponse)
    }

    @PutMapping(
        value = ["/api/types/{idtipe}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateVehicleType(
        @PathVariable("idtipe") id: Long,
        @RequestBody updateTypeRequest: UpdateTypeRequest
    ): WebResponse<TypeResponse> {
        val vehicletypeResponse = vehicleTypeService.update(id, updateTypeRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = vehicletypeResponse
        )
    }

    @DeleteMapping(
        value = ["/api/types/{idtipe}"],
        produces = ["application/json"]
    )
    fun deleteVehicleType(@PathVariable("idtipe") id: Long): WebResponse<String>{
        val hapus =  vehicleTypeService.delete(id).toString()
        return WebResponse(
            code = 200,
            status = "OK",
            data = hapus
        )
    }

    @GetMapping(
        value = ["/api/types"],
        produces = ["application/json"]
    )
    fun listVehicleType(@RequestParam(value = "size", defaultValue = "10") size:Int,
                     @RequestParam(value = "page", defaultValue = "0")page:Int): WebResponse<List<TypeResponse>> {
        val request = ListTypeRequest(page = page, size = size)
        val responses = vehicleTypeService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )

    }

}
