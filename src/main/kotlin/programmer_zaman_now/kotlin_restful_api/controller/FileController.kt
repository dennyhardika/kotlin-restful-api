package programmer_zaman_now.kotlin_restful_api.controller

import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.nio.file.Path
import java.nio.file.Paths

@RestController
@RequestMapping("/uploads")
class FileController {

    private val uploadDir: Path = Paths.get("/home/uploads")

    @GetMapping("/{filename}")
    fun getFile(@PathVariable filename: String): ResponseEntity<Resource> {
        val file = uploadDir.resolve(filename)
        val resource = UrlResource(file.toUri())

        return if (resource.exists() || resource.isReadable) {
            ResponseEntity.ok(resource)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
