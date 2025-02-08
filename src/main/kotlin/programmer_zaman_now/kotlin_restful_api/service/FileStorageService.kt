package programmer_zaman_now.kotlin_restful_api.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileStorageService {

    private val uploadDir: Path = Paths.get("/app/uploads") // Direktori penyimpanan di dalam container

    init {
        Files.createDirectories(uploadDir) // Pastikan direktori tersedia saat aplikasi dijalankan
    }

    fun saveFile(file: MultipartFile): String {
        val fileName = System.currentTimeMillis().toString() + "_" + file.originalFilename
        val filePath = uploadDir.resolve(fileName)

        println("📂 Menyimpan file: $fileName di lokasi: $filePath")

        Files.copy(file.inputStream, filePath, StandardCopyOption.REPLACE_EXISTING)

        println("✅ File berhasil disimpan!")

        return "/uploads/$fileName" // Path yang akan disimpan di database
    }

    fun deleteFile(filename: String?) {
        if (!filename.isNullOrEmpty()) {
            // Pastikan path sesuai dengan lokasi penyimpanan file
            val file = File(uploadDir.toFile(), filename.substringAfterLast("/"))

            if (file.exists()) {
                if (file.delete()) {
                    println("✅ File $filename berhasil dihapus!")
                } else {
                    println("⚠️ Gagal menghapus file $filename")
                }
            } else {
                println("❌ File $filename tidak ditemukan di ${file.absolutePath}")
            }
        }
    }
}
