package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import programmer_zaman_now.kotlin_restful_api.entity.User
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.uprofile.CreateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.ListUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UpdateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UprofileResponse
import programmer_zaman_now.kotlin_restful_api.repository.UprofileRepository
import programmer_zaman_now.kotlin_restful_api.service.UprofileService
import java.util.Date
import java.util.stream.Collectors

@Service
class UprofileServiceImpl(val uprofileRepository: UprofileRepository): UprofileService {
    override fun create(
        createUprofileRequest: CreateUprofileRequest, user: User): UprofileResponse {
//        if (uprofileRepository.findByIdOrNull(user.iduser) != null) {
//            throw IllegalArgumentException("User already has a profile")
//        }
        val uprofile = Uprofile(
            namalengkap = createUprofileRequest.namalengkap!!,
            jeniskendaraan = createUprofileRequest.jeniskendaraan!!,
            alamat = createUprofileRequest.alamat!!,
            nohandphone = createUprofileRequest.nohandphone!!,
            fotoprofil = createUprofileRequest.fotoprofil!!,
            fotokendaraan = createUprofileRequest.fotokendaraan!!,
            createdAt = Date(),
            updatedAt = null,
            user = user
        )
        uprofileRepository.save(uprofile)

        return UprofileResponse(
            iduprofile = uprofile.iduprofile!!,
            namalengkap = uprofile.namalengkap,
            jeniskendaraan = uprofile.jeniskendaraan,
            alamat = uprofile.alamat,
            nohandphone = uprofile.nohandphone,
            fotoprofil = uprofile.fotoprofil,
            fotokendaraan = uprofile.fotokendaraan,
            createdAt = uprofile.createdAt,
            updatedAt = uprofile.updatedAt,
            user = user.iduser!!
        )
        }

    override fun get(id: Long): UprofileResponse {
        val uprofile = findUprofileByOrThrowNotFound(id)
        return convertUprofileToUprofileResponse(uprofile)
    }

    override fun update(id: Long, updateUprofileRequest: UpdateUprofileRequest): UprofileResponse {
        val uprofile = findUprofileByOrThrowNotFound(id)

        uprofile.apply {
            namalengkap = updateUprofileRequest.namalengkap!!
            jeniskendaraan = updateUprofileRequest.jeniskendaraan!!
            alamat = updateUprofileRequest.alamat!!
            nohandphone = updateUprofileRequest.nohandphone!!
            fotoprofil = updateUprofileRequest.fotoprofil!!
            fotokendaraan = updateUprofileRequest.fotokendaraan!!
            updatedAt = Date()
        }

        uprofileRepository.save(uprofile)

        return convertUprofileToUprofileResponse(uprofile)
    }

    override fun delete(id: Long) {
        val uprofile = findUprofileByOrThrowNotFound(id)
        uprofileRepository.delete(uprofile)
    }

    override fun list(listUprofileRequest: ListUprofileRequest): List<UprofileResponse> {
        val page = uprofileRepository.findAll(PageRequest.of(listUprofileRequest.page, listUprofileRequest.size))
        val uprofiles: List<Uprofile> = page.get().collect(Collectors.toList())

        return uprofiles.map { convertUprofileToUprofileResponse(it) }
    }

    private fun findUprofileByOrThrowNotFound(id: Long): Uprofile {
        val uprofile = uprofileRepository.findByIdOrNull(id)
        if (uprofile == null){
            throw NotFoundExpection()
        }else {
            return uprofile;
        }
    }
    private fun convertUprofileToUprofileResponse(uprofile: Uprofile): UprofileResponse {
        return UprofileResponse(
            iduprofile = uprofile.iduprofile!!,
            namalengkap = uprofile.namalengkap,
            jeniskendaraan = uprofile.jeniskendaraan,
            alamat = uprofile.alamat,
            nohandphone = uprofile.nohandphone,
            fotoprofil = uprofile.fotoprofil,
            fotokendaraan = uprofile.fotokendaraan,
            createdAt = uprofile.createdAt,
            updatedAt = uprofile.updatedAt,
            user = uprofile.user?.iduser ?: throw IllegalStateException("User is null in Uprofile")
        )
    }
}
