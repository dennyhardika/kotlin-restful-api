package programmer_zaman_now.kotlin_restful_api.service.impl

import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import programmer_zaman_now.kotlin_restful_api.entity.User
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Group
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.VehicleType
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.uprofile.CreateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.ListUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UpdateUprofileRequest
import programmer_zaman_now.kotlin_restful_api.model.uprofile.UprofileResponse
import programmer_zaman_now.kotlin_restful_api.repository.GroupRepository
import programmer_zaman_now.kotlin_restful_api.repository.TypeRepository
import programmer_zaman_now.kotlin_restful_api.repository.UprofileRepository
import programmer_zaman_now.kotlin_restful_api.repository.UserRepository
import programmer_zaman_now.kotlin_restful_api.service.FileStorageService
import programmer_zaman_now.kotlin_restful_api.service.UprofileService
import java.util.Date
import java.util.stream.Collectors

@Service
class UprofileServiceImpl(val uprofileRepository: UprofileRepository, val userRepository: UserRepository, val fileStorageService: FileStorageService, val groupRepository: GroupRepository): UprofileService {

    override fun create(
        createUprofileRequest: CreateUprofileRequest, fotoProfil: MultipartFile?,
        fotoKendaraan: MultipartFile?, user: User, group: Group
    ): UprofileResponse {
//        if (uprofileRepository.findByIdOrNull(user.iduser) != null) {
//            throw IllegalArgumentException("User already has a profile")
//        }
        // Upload file jika ada, simpan path ke database
        val fotoProfilPath = fotoProfil?.let { fileStorageService.saveFile(it) } ?: ""
        val fotoKendaraanPath = fotoKendaraan?.let { fileStorageService.saveFile(it) } ?: ""

        val uprofile = Uprofile(
            namalengkap = createUprofileRequest.namalengkap!!,
            tipekendaraan = createUprofileRequest.tipekendaraan!!,
            merekkendaraan = createUprofileRequest.merekkendaraan!!,
            noplat = createUprofileRequest.noplat!!,
            alamat = createUprofileRequest.alamat ?: "",
            nohandphone = createUprofileRequest.nohandphone!!,
//            fotoprofil = createUprofileRequest.fotoprofil!!,
//            fotokendaraan = createUprofileRequest.fotokendaraan!!,
            fotoprofil = fotoProfilPath,
            fotokendaraan = fotoKendaraanPath,
            createdAt = Date(),
            updatedAt = null,
            user = user,
            group = group
        )
        uprofileRepository.save(uprofile)

        return UprofileResponse(
            iduprofile = uprofile.iduprofile!!,
            namalengkap = uprofile.namalengkap,
            tipekendaraan = uprofile.tipekendaraan,
            merekkendaraan = uprofile.merekkendaraan,
            noplat = uprofile.noplat,
            alamat = uprofile.alamat ?: "",
            nohandphone = uprofile.nohandphone,
            fotoprofil = uprofile.fotoprofil ?: "",
            fotokendaraan = uprofile.fotokendaraan ?: "",
            createdAt = uprofile.createdAt,
            updatedAt = uprofile.updatedAt,
            group = group.idgrup!!,
            user = user.iduser!!
        )
        }

    override fun get(id: Long): UprofileResponse {
        val uprofile = findUprofileByOrThrowNotFound(id)
        return convertUprofileToUprofileResponse(uprofile)
    }

    override fun update(
        id: Long,
        updateUprofileRequest: UpdateUprofileRequest,
        fotoProfil: MultipartFile?,
        fotoKendaraan: MultipartFile?
    ): UprofileResponse {
        val uprofile = findUprofileByOrThrowNotFound(id)

        uprofile.apply {
            namalengkap = updateUprofileRequest.namalengkap!!
            tipekendaraan = updateUprofileRequest.tipekendaraan!!
            merekkendaraan = updateUprofileRequest.merekkendaraan!!
            noplat = updateUprofileRequest.noplat!!
            alamat = updateUprofileRequest.alamat ?: ""
            nohandphone = updateUprofileRequest.nohandphone!!
            updatedAt = Date()

            // **Hapus file lama jika ada dan pengguna mengupload file baru**
            if (fotoProfil != null) {
                uprofile.fotoprofil?.let { oldFile ->
                    if (oldFile.isNotEmpty()) {
                        fileStorageService.deleteFile(oldFile)
                    }
                }
                fotoprofil = fileStorageService.saveFile(fotoProfil)
            }

            if (fotoKendaraan != null) {
                uprofile.fotokendaraan?.let { oldFile ->
                    if (oldFile.isNotEmpty()) {
                        fileStorageService.deleteFile(oldFile)
                    }
                }
                fotokendaraan = fileStorageService.saveFile(fotoKendaraan)
            }
        }

        uprofileRepository.save(uprofile)

        return convertUprofileToUprofileResponse(uprofile)
    }

    @Transactional
    override fun delete(id: Long) {
        val uprofile = findUprofileByOrThrowNotFound(id)
        println("Menghapus Uprofile dengan ID: $id")
        uprofileRepository.delete(uprofile)
        uprofileRepository.flush() // Memastikan perubahan langsung dikirim ke DB
        println("Uprofile dengan ID: $id berhasil dihapus")
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
            tipekendaraan = uprofile.tipekendaraan,
            merekkendaraan = uprofile.merekkendaraan,
            noplat = uprofile.noplat,
            alamat = uprofile.alamat ?: "",
            nohandphone = uprofile.nohandphone,
            fotoprofil = uprofile.fotoprofil ?: "",
            fotokendaraan = uprofile.fotokendaraan ?: "",
            createdAt = uprofile.createdAt,
            updatedAt = uprofile.updatedAt,
            group = uprofile.group?.idgrup ?: throw IllegalStateException("Group is null in Uprofile"),
            user = uprofile.user?.iduser ?: throw IllegalStateException("User is null in Uprofile")
        )
    }
}
