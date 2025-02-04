package programmer_zaman_now.kotlin_restful_api.repository

import org.springframework.data.jpa.repository.JpaRepository
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile

interface UprofileRepository: JpaRepository<Uprofile, Long> {
}