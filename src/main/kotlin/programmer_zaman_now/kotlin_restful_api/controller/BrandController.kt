package programmer_zaman_now.kotlin_restful_api.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import programmer_zaman_now.kotlin_restful_api.model.WebResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.BrandResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.CreateBrandRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.ListBrandRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.UpdateBrandRequest
import programmer_zaman_now.kotlin_restful_api.service.BrandService

@RestController
class BrandController(val brandService: BrandService) {

    @PostMapping(
        value = ["/api/brands"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createBrand(@RequestBody body: CreateBrandRequest): WebResponse<BrandResponse> {
        val brandResponse = brandService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = brandResponse
        )
    }

    @GetMapping(
        value = ["/api/brands/name/{mrkknd}"],
        produces = ["application/json"]
    )
    fun getBrandName(@PathVariable("mrkknd") mrkknd: String): WebResponse<BrandResponse> {
        val brandResponse = brandService.getmerekknd(mrkknd)
        return WebResponse(
            code = 200,
            status = "OK",
            data = brandResponse
        )
    }

    @GetMapping(
        value = ["/api/brands/id/{idmerek}"],
        produces = ["application/json"]
    )
    fun getBrand(@PathVariable("idmerek") id: Long): WebResponse<BrandResponse> {
        val brandResponse = brandService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = brandResponse
        )
    }

    @PutMapping(
        value = ["api/brands/{idmerek}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateBrand(@PathVariable("idmerek") id: Long,
                       @RequestBody updateBrandRequest: UpdateBrandRequest
    ): WebResponse<BrandResponse> {
        val brandResponse = brandService.update(id, updateBrandRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = brandResponse
        )
    }

    @DeleteMapping(
        value = ["/api/brands/{idmerek}"],
        produces = ["application/json"]
    )
    fun deleteBrand(@PathVariable("idmerek") id: Long): WebResponse<String>{
        val hapus =  brandService.delete(id).toString()
        return WebResponse(
            code = 200,
            status = "OK",
            data = hapus
        )
    }

    @GetMapping(
        value = ["/api/brands"],
        produces = ["application/json"]
    )
    fun listBrand(@RequestParam(value = "size", defaultValue = "10") size:Int,
                       @RequestParam(value = "page", defaultValue = "0")page:Int): WebResponse<List<BrandResponse>> {
        val request = ListBrandRequest(page = page, size = size)
        val responses = brandService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )

    }

}