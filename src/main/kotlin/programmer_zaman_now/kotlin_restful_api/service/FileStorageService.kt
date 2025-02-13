package programmer_zaman_now.kotlin_restful_api.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.UUID

@Service
class FileStorageService {

    private val uploadDir: Path = Paths.get("uploads") // Direktori penyimpanan

    init {
        Files.createDirectories(uploadDir)
    }

    fun saveFile(file: MultipartFile): String {
        val fileName = System.currentTimeMillis().toString() + "_" + file.originalFilename
        val filePath = uploadDir.resolve(fileName)

        Files.copy(file.inputStream, filePath, StandardCopyOption.REPLACE_EXISTING)

        return "/uploads/$fileName" // Path yang akan disimpan di database
    }
}