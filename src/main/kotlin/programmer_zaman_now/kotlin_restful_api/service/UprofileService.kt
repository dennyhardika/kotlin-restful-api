package programmer_zaman_now.kotlin_restful_api.service

import org.springframework.web.multipart.MultipartFile
import programmer_zaman_now.kotlin_restful_api.entity.User
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Group
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.VehicleType
import programmer_zaman_now.kotlin_restful_api.model.product.ProductResponse
import programmer_zaman_now.kotlin_restful_api.model.uprofile.CreateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.ListUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UpdateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UprofileResponse

interface UprofileService {

    fun create(
        createUprofileRequest: CreateUprofileRequest, fotoProfil: MultipartFile?,
        fotoKendaraan: MultipartFile?, user: User, group: Group
    ): UprofileResponse

    fun get(id: Long): UprofileResponse

    fun getnamalkp(namaLengkap: String): UprofileResponse

    fun getUprofilesByUser(userId: Long): List<UprofileResponse>

    fun update(id: Long, updateUprofileRequest: UpdateUprofileRequest, fotoProfil: MultipartFile?, fotoKendaraan: MultipartFile?): UprofileResponse

    fun delete(id: Long)

    fun list(listUprofileRequest: ListUprofileRequest): List<UprofileResponse>

}
