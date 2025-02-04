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
        if (uprofileRepository.findByIdOrNull(user.id_user) != null) {
            throw IllegalArgumentException("User already has a profile")
        }
        val uprofile = Uprofile(
            nama_lengkap = createUprofileRequest.nama_lengkap!!,
            jenis_kendaraan = createUprofileRequest.jenis_kendaraan!!,
            alamat = createUprofileRequest.alamat!!,
            no_handphone = createUprofileRequest.no_handphone!!,
            foto_profil = createUprofileRequest.foto_profil!!,
            foto_kendaraan = createUprofileRequest.foto_kendaraan!!,
            createdAt = Date(),
            updatedAt = null,
            user = user
        )
        uprofileRepository.save(uprofile)

        return UprofileResponse(
            id_uprofile = uprofile.id_uprofile!!,
            nama_lengkap = uprofile.nama_lengkap,
            jenis_kendaraan = uprofile.jenis_kendaraan,
            alamat = uprofile.alamat,
            no_handphone = uprofile.no_handphone,
            foto_profil = uprofile.foto_profil,
            foto_kendaraan = uprofile.foto_kendaraan,
            createdAt = uprofile.createdAt,
            updatedAt = uprofile.updatedAt,
            user = user.id_user!!
        )
        }

    override fun get(id: Long): UprofileResponse {
        val uprofile = findUprofileByOrThrowNotFound(id)
        return convertUprofileToUprofileResponse(uprofile)
    }

    override fun update(id: Long, updateUprofileRequest: UpdateUprofileRequest): UprofileResponse {
        val uprofile = findUprofileByOrThrowNotFound(id)

        uprofile.apply {
            nama_lengkap = updateUprofileRequest.nama_lengkap!!
            jenis_kendaraan = updateUprofileRequest.jenis_kendaraan!!
            alamat = updateUprofileRequest.alamat!!
            no_handphone = updateUprofileRequest.no_handphone!!
            foto_profil = updateUprofileRequest.foto_profil!!
            foto_kendaraan = updateUprofileRequest.foto_kendaraan!!
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
            id_uprofile = uprofile.id_uprofile!!,
            nama_lengkap = uprofile.nama_lengkap,
            jenis_kendaraan = uprofile.jenis_kendaraan,
            alamat = uprofile.alamat,
            no_handphone = uprofile.no_handphone,
            foto_profil = uprofile.foto_profil,
            foto_kendaraan = uprofile.foto_kendaraan,
            createdAt = uprofile.createdAt,
            updatedAt = uprofile.updatedAt,
            user = uprofile.user?.id_user ?: throw IllegalStateException("User is null in Uprofile")
        )
    }
}