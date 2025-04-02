package programmer_zaman_now.kotlin_restful_api.entity.kendaraan

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import java.util.Date

@Entity
@Table(name = "Groups")
class Group (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grup")
    val idgrup: Long? = null,

    @Column(name = "grup_kendaraan")
    var grupkendaraan: String,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    @Column(name = "updated_at")
    var updatedAt: Date? = null,

    @OneToMany(mappedBy = "group", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JsonIgnore  // Hindari infinite loop saat serialize ke JSON
    val uprofile: List<Uprofile> = mutableListOf(),  // Satu VehicleType bisa digunakan banyak Uprofile


    @OneToMany(mappedBy = "group", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, targetEntity = VehicleType::class)
    @JsonIgnore
    var vehicleTypes: MutableList<VehicleType> = mutableListOf()  // Perbaiki tipe menjadi MutableList<VehicleType>
)
