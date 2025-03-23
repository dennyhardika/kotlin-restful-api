package programmer_zaman_now.kotlin_restful_api.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

@RestController
@RequestMapping("/uploads")
class FileController {

    private val uploadDir: Path = Paths.get("/home/uploads")

    @GetMapping("/uploads/{filename}")
    fun getFile(@PathVariable filename: String, request: HttpServletRequest): ResponseEntity<Resource> {
        val file = File("/home/uploads/$filename")
        if (!file.exists()) {
            return ResponseEntity.notFound().build()
        }

        val resource = FileSystemResource(file)
        val mimeType = request.servletContext.getMimeType(file.absolutePath) ?: "application/octet-stream"

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(mimeType))
            .body(resource)
    }
}
