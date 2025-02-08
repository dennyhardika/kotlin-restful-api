package programmer_zaman_now.kotlin_restful_api.entity.kendaraan

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "Brands")
class Brand (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_merek")
    val idmerek: Long? = null,

    @Column(name = "merek_kendaraan")
    var merekkendaraan: String,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    @Column(name = "updated_at")
    var updatedAt: Date? = null,

    @OneToMany(mappedBy = "brands", orphanRemoval = true, cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var types: MutableList<VehicleType> = mutableListOf()

)