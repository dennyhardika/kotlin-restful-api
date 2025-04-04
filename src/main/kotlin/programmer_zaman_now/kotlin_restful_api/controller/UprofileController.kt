package programmer_zaman_now.kotlin_restful_api.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import programmer_zaman_now.kotlin_restful_api.model.WebResponse
import programmer_zaman_now.kotlin_restful_api.model.uprofile.CreateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.ListUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UpdateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UprofileResponse
import programmer_zaman_now.kotlin_restful_api.repository.GroupRepository
import programmer_zaman_now.kotlin_restful_api.repository.TypeRepository
import programmer_zaman_now.kotlin_restful_api.repository.UserRepository
import programmer_zaman_now.kotlin_restful_api.service.UprofileService
import programmer_zaman_now.kotlin_restful_api.service.FileStorageService

@RestController
class UprofileController(val uprofileService: UprofileService, val userRepository: UserRepository, val fileStorageService: FileStorageService, val groupRepository: GroupRepository) {

    @Throws(IllegalArgumentException::class)
    @PostMapping(
        value = ["/api/uprofiles"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"] // Ubah menjadi multipart
    )
    fun createUprofile(
        @RequestParam("namalengkap") namalengkap: String,
        @RequestParam("tipekendaraan") tipekendaraan: String,
        @RequestParam("merekkendaraan") merekkendaraan: String,
        @RequestParam("noplat") noplat: String,
        @RequestParam("alamat") alamat: String,
        @RequestParam("nohandphone") nohandphone: String,
        @RequestParam("user") userId: Long,
        @RequestParam("group") id: Long,
        @RequestParam("fotoprofil", required = false) fotoprofil: MultipartFile?, // Ubah ke opsional
        @RequestParam("fotokendaraan", required = false) fotokendaraan: MultipartFile? // Ubah ke opsional
    ): WebResponse<UprofileResponse> {

        val tipe = groupRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("Type dengan ID $id tidak ditemukan")
        val user = userRepository.findByIdOrNull(userId)
            ?: throw IllegalArgumentException("User dengan ID $userId tidak ditemukan")

        // Buat request tanpa langsung menyimpan file
        val request = CreateUprofileRequest(
            namalengkap = namalengkap,
            tipekendaraan  = tipekendaraan,
            merekkendaraan = merekkendaraan,
            noplat = noplat,
            alamat = alamat,
            nohandphone = nohandphone,
            fotoprofil = "", // Tidak perlu simpan di sini
            fotokendaraan = "",
            user = user.iduser!!,
            group = tipe.idgrup!!
        )

        // File hanya disimpan dalam `UprofileServiceImpl.create()`
        val uprofileResponse = uprofileService.create(request, fotoprofil, fotokendaraan, user, tipe)

        return WebResponse(
            code = 200,
            status = "OK",
            data = uprofileResponse
        )
    }

    @GetMapping(
        value = ["/api/uprofiles/{idUprofile}"],
        produces = ["application/json"]
    )
    fun getUprofile(@PathVariable("idUprofile") id: Long): WebResponse<UprofileResponse> {
        val uprofileResponse = uprofileService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = uprofileResponse
        )
    }

@PutMapping(
    value = ["/api/uprofiles/{idUprofile}"],
    produces = ["application/json"],
    consumes = ["multipart/form-data"]
)
fun updateUprofile(
    @PathVariable("idUprofile") id: Long,
    @RequestParam("namalengkap") namalengkap: String,
    @RequestParam("tipekendaraan") tipekendaraan: String,
    @RequestParam("merekkendaraan") merekkendaraan: String,
    @RequestParam("noplat") noplat: String,
    @RequestParam("alamat") alamat: String,
    @RequestParam("nohandphone") nohandphone: String,
    @RequestParam("fotoprofil", required = false) fotoprofil: MultipartFile?,
    @RequestParam("fotokendaraan", required = false) fotokendaraan: MultipartFile?
): WebResponse<UprofileResponse> {

    val request = UpdateUprofileRequest(
        namalengkap = namalengkap,
        tipekendaraan = tipekendaraan,
        merekkendaraan = merekkendaraan,
        noplat = noplat,
        alamat = alamat,
        nohandphone = nohandphone,
        fotoprofil = "", // Biarkan service yang handle upload
        fotokendaraan = ""
    )

    val uprofileResponse = uprofileService.update(id, request, fotoprofil, fotokendaraan)
    return WebResponse(
        code = 200,
        status = "OK",
        data = uprofileResponse
    )
}

    @DeleteMapping(
        value = ["/api/uprofiles/{idUprofile}"],
        produces = ["application/json"]
    )
    fun deleteUprofile(@PathVariable("idUprofile") id: Long): WebResponse<String>{
        val hapus =  uprofileService.delete(id).toString()
        return WebResponse(
            code = 200,
            status = "OK",
            data = hapus
        )
    }

    @GetMapping(
        value = ["/api/uprofiles"],
        produces = ["application/json"]
    )
    fun listUprofiles(@RequestParam(value = "size", defaultValue = "10") size:Int,
                     @RequestParam(value = "page", defaultValue = "0")page:Int): WebResponse<List<UprofileResponse>> {
        val request = ListUprofileRequest(page = page, size = size)
        val responses = uprofileService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )

    }
}
