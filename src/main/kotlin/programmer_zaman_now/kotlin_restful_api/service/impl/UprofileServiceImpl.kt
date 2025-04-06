package programmer_zaman_now.kotlin_restful_api.service.impl

import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import programmer_zaman_now.kotlin_restful_api.entity.User
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Group
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.uprofile.*
import programmer_zaman_now.kotlin_restful_api.repository.*
import programmer_zaman_now.kotlin_restful_api.service.FileStorageService
import programmer_zaman_now.kotlin_restful_api.service.UprofileService
import java.util.*
import java.util.stream.Collectors

@Service
class UprofileServiceImpl(
    val uprofileRepository: UprofileRepository,
    val userRepository: UserRepository,
    val fileStorageService: FileStorageService,
    val groupRepository: GroupRepository
) : UprofileService {

    override fun create(
        createUprofileRequest: CreateUprofileRequest,
        fotoProfil: MultipartFile?,
        fotoKendaraan: MultipartFile?,
        user: User,
        group: Group
    ): UprofileResponse {
        val fotoProfilFile = fotoProfil?.let { fileStorageService.saveFile(it) } ?: ""
        val fotoKendaraanFile = fotoKendaraan?.let { fileStorageService.saveFile(it) } ?: ""

        val uprofile = Uprofile(
            namalengkap = createUprofileRequest.namalengkap!!,
            tipekendaraan = createUprofileRequest.tipekendaraan!!,
            merekkendaraan = createUprofileRequest.merekkendaraan!!,
            noplat = createUprofileRequest.noplat!!,
            alamat = createUprofileRequest.alamat ?: "",
            nohandphone = createUprofileRequest.nohandphone!!,
            fotoprofil = fotoProfilFile,
            fotokendaraan = fotoKendaraanFile,
            createdAt = Date(),
            updatedAt = null,
            user = user,
            group = group
        )
        uprofileRepository.save(uprofile)

        return convertUprofileToUprofileResponse(uprofile)
    }

    override fun update(
        id: Long,
        updateUprofileRequest: UpdateUprofileRequest,
        fotoProfil: MultipartFile?,
        fotoKendaraan: MultipartFile?
    ): UprofileResponse {
        val uprofile = findUprofileByOrThrowNotFound(id)

        // Ambil group berdasarkan idGrup dari request
        val grp = groupRepository.findById(updateUprofileRequest.group!!)
            .orElseThrow { throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Group tidak ditemukan") }
        uprofile.apply {
            namalengkap = updateUprofileRequest.namalengkap!!
            tipekendaraan = updateUprofileRequest.tipekendaraan!!
            merekkendaraan = updateUprofileRequest.merekkendaraan!!
            noplat = updateUprofileRequest.noplat!!
            alamat = updateUprofileRequest.alamat ?: ""
            nohandphone = updateUprofileRequest.nohandphone!!
            updatedAt = Date()
            this.group = grp

            // Hapus & ganti foto profil jika ada
            if (fotoProfil != null) {
                if (!fotoprofil.isNullOrEmpty()) fileStorageService.deleteFile(fotoprofil!!)
                fotoprofil = fileStorageService.saveFile(fotoProfil)
            }

            // Hapus & ganti foto kendaraan jika ada
            if (fotoKendaraan != null) {
                if (!fotokendaraan.isNullOrEmpty()) fileStorageService.deleteFile(fotokendaraan!!)
                fotokendaraan = fileStorageService.saveFile(fotoKendaraan)
            }
        }

        uprofileRepository.save(uprofile)
        return convertUprofileToUprofileResponse(uprofile)
    }

    @Transactional
    override fun delete(id: Long) {
        val uprofile = findUprofileByOrThrowNotFound(id)

        // Hapus file dari server
        fileStorageService.deleteFile(uprofile.fotoprofil)
        fileStorageService.deleteFile(uprofile.fotokendaraan)

        uprofileRepository.delete(uprofile)
        uprofileRepository.flush()
    }

    override fun get(id: Long): UprofileResponse {
        val uprofile = findUprofileByOrThrowNotFound(id)
        return convertUprofileToUprofileResponse(uprofile)
    }

    override fun list(listUprofileRequest: ListUprofileRequest): List<UprofileResponse> {
        val page = uprofileRepository.findAll(PageRequest.of(listUprofileRequest.page, listUprofileRequest.size))
        return page.get().map { convertUprofileToUprofileResponse(it) }.toList()
    }

    private fun findUprofileByOrThrowNotFound(id: Long): Uprofile {
        return uprofileRepository.findByIdOrNull(id) ?: throw NotFoundExpection()
    }

    private fun convertUprofileToUprofileResponse(uprofile: Uprofile): UprofileResponse {
        return UprofileResponse(
            iduprofile = uprofile.iduprofile!!,
            namalengkap = uprofile.namalengkap,
            tipekendaraan = uprofile.tipekendaraan,
            merekkendaraan = uprofile.merekkendaraan,
            noplat = uprofile.noplat,
            alamat = uprofile.alamat ?: "",
            nohandphone = uprofile.nohandphone,
            fotoprofil = if (!uprofile.fotoprofil.isNullOrEmpty()) fileStorageService.generateFileUrl(uprofile.fotoprofil!!) else "",
            fotokendaraan = if (!uprofile.fotokendaraan.isNullOrEmpty()) fileStorageService.generateFileUrl(uprofile.fotokendaraan!!) else "",
            createdAt = uprofile.createdAt,
            updatedAt = uprofile.updatedAt,
            group = uprofile.group?.idgrup ?: throw IllegalStateException("Group is null"),
            user = uprofile.user?.iduser ?: throw IllegalStateException("User is null")
        )
    }
}
