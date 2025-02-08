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
import programmer_zaman_now.kotlin_restful_api.model.packet.CreatePacketRequest
import programmer_zaman_now.kotlin_restful_api.model.packet.ListPacketRequest
import programmer_zaman_now.kotlin_restful_api.model.packet.PacketResponse
import programmer_zaman_now.kotlin_restful_api.model.packet.UpdatePacketRequest
import programmer_zaman_now.kotlin_restful_api.service.PacketService

@RestController
class PacketController(val packetService: PacketService) {

    @PostMapping(
        value = ["/api/packages"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createPacket(@RequestBody body: CreatePacketRequest): WebResponse<PacketResponse> {
        val packetResponse = packetService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = packetResponse
        )
    }

    @GetMapping(
        value = ["/api/packages/name/{nmpkt}"],
        produces = ["application/json"]
    )
    fun getPacketName(@PathVariable("nmpkt") nmpkt: String): WebResponse<PacketResponse> {
        val packetResponse = packetService.getnamapkt(nmpkt)
        return WebResponse(
            code = 200,
            status = "OK",
            data = packetResponse
        )
    }

    @GetMapping(
        value = ["/api/packages/id/{idpaket}"],
        produces = ["application/json"]
    )
    fun getPacket(@PathVariable("idpaket") id: Long): WebResponse<PacketResponse> {
        val packetResponse = packetService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = packetResponse
        )
    }

    @PutMapping(
        value = ["api/packages/{idpaket}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updatePacket(@PathVariable("idpaket") id: Long,
                    @RequestBody updatePacketRequest: UpdatePacketRequest
    ): WebResponse<PacketResponse> {
        val packetResponse = packetService.update(id, updatePacketRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = packetResponse
        )
    }

    @DeleteMapping(
        value = ["/api/packages/{idpaket}"],
        produces = ["application/json"]
    )
    fun deletePacket(@PathVariable("idpaket") id: Long): WebResponse<String>{
        val hapus =  packetService.delete(id).toString()
        return WebResponse(
            code = 200,
            status = "OK",
            data = hapus
        )
    }

    @GetMapping(
        value = ["/api/packages"],
        produces = ["application/json"]
    )
    fun listPacket(@RequestParam(value = "size", defaultValue = "10") size:Int,
                  @RequestParam(value = "page", defaultValue = "0")page:Int): WebResponse<List<PacketResponse>> {
        val request = ListPacketRequest(page = page, size = size)
        val responses = packetService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )

    }
}