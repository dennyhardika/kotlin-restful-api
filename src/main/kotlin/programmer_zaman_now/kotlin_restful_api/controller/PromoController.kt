package programmer_zaman_now.kotlin_restful_api.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import programmer_zaman_now.kotlin_restful_api.entity.Category
import programmer_zaman_now.kotlin_restful_api.model.WebResponse
import programmer_zaman_now.kotlin_restful_api.model.promo.CreatePromoRequest
import programmer_zaman_now.kotlin_restful_api.model.promo.ListPromoRequest
import programmer_zaman_now.kotlin_restful_api.model.promo.PromoResponse
import programmer_zaman_now.kotlin_restful_api.model.promo.UpdatePromoRequest
import programmer_zaman_now.kotlin_restful_api.service.PromoService

@RestController
class PromoController(val promoService: PromoService) {

    @PostMapping(
        value = ["/api/promotions"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createPromo(@RequestBody body: CreatePromoRequest): WebResponse<PromoResponse> {
        val promoService = promoService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = promoService
        )
    }

    @GetMapping(
        value = ["/api/promotions/name/{nmprm}"],
        produces = ["application/json"]
    )
    fun getPromoName(@PathVariable("nmprm") nmprm: String): WebResponse<PromoResponse> {
        val promoService = promoService.getnamapromo(nmprm)
        return WebResponse(
            code = 200,
            status = "OK",
            data = promoService
        )
    }

    @GetMapping(
        value = ["/api/promotions/id/{idpromo}"],
        produces = ["application/json"]
    )
    fun getPromo(@PathVariable("idpromo") id: Long): WebResponse<PromoResponse> {
        val promoService = promoService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = promoService
        )
    }

    @PutMapping(
        value = ["api/promotions/{idpromo}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updatePromo(@PathVariable("idpromo") id: Long,
                     @RequestBody updatePromoRequest: UpdatePromoRequest
    ): WebResponse<PromoResponse> {
        val promoService = promoService.update(id, updatePromoRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = promoService
        )
    }

    @DeleteMapping(
        value = ["/api/promotions/{idpromo}"],
        produces = ["application/json"]
    )
    fun deletePromo(@PathVariable("idpromo") id: Long): WebResponse<String>{
        val hapus =  promoService.delete(id).toString()
        return WebResponse(
            code = 200,
            status = "OK",
            data = hapus
        )
    }

    @GetMapping(
        value = ["/api/promotions"],
        produces = ["application/json"]
    )
    fun listPromo(@RequestParam(value = "size", defaultValue = "10") size:Int,
                   @RequestParam(value = "page", defaultValue = "0")page:Int): WebResponse<List<PromoResponse>> {
        val request = ListPromoRequest(page = page, size = size)
        val responses = promoService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )

    }
}