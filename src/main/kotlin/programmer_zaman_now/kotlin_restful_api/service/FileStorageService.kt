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

    private val serverIp = "http://192.168.1.100:8080" // Ganti dengan IP server backend
    private val uploadDir: Path = Paths.get("/home/uploads") // Direktori penyimpanan langsung di server

    init {
        Files.createDirectories(uploadDir) // Pastikan direktori tersedia saat aplikasi dijalankan
    }

    fun saveFile(file: MultipartFile): String {
        val fileName = System.currentTimeMillis().toString() + "_" + file.originalFilename
        val filePath = uploadDir.resolve(fileName)

        println("📂 Menyimpan file: $fileName di lokasi: $filePath")

        Files.copy(file.inputStream, filePath, StandardCopyOption.REPLACE_EXISTING)

        println("✅ File berhasil disimpan!")

        return fileName // hanya nama file
    }

    fun deleteFile(fileName: String?) {
        if (!fileName.isNullOrEmpty()) {
            val file = File(uploadDir.toFile(), fileName)

            if (file.exists()) {
                if (file.delete()) {
                    println("✅ File $fileName berhasil dihapus!")
                } else {
                    println("⚠️ Gagal menghapus file $fileName")
                }
            } else {
                println("❌ File $fileName tidak ditemukan di ${file.absolutePath}")
            }
        }
    }


    fun generateFileUrl(fileName: String): String {
        return "$serverIp/uploads/$fileName"
    }

}
