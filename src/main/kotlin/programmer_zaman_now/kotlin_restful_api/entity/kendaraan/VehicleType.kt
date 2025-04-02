package programmer_zaman_now.kotlin_restful_api.entity.kendaraan

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import programmer_zaman_now.kotlin_restful_api.entity.Category
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import java.util.Date

@Entity
@Table(name = "Types")
class VehicleType (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipe")
    val idtipe: Long? = null,

    @Column(name = "tipe_kendaraan")
    var tipekendaraan: String,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    @Column(name = "updated_at")
    var updatedAt: Date? = null,

//    @OneToOne(mappedBy = "vehicleType", orphanRemoval = true, cascade = [CascadeType.ALL])
//    var uprofiles: Uprofile? = null,

    @ManyToOne
    @JoinColumn(name = "id_merek", nullable = false)
    var brands: Brand,

    @ManyToOne
    @JoinColumn(name = "id_grup", nullable = false)  // Foreign Key ke Group
    var group: Group

) 
