package programmer_zaman_now.kotlin_restful_api.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Group
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.VehicleType
import java.util.Date

@Entity
@Table(name = "Uprofiles")
class Uprofile (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_uprofile")
    val iduprofile: Long? = null,

    @Column(name = "nama_lengkap")
    var namalengkap: String,

    @Column(name = "tipe_kendaraan")
    var tipekendaraan: String,

    @Column(name = "merek_kendaraan")
    var merekkendaraan: String,

    @Column(name = "no_plat")
    var noplat: String,

    @Column(name = "alamat")
    var alamat: String? = null,

    @Column(name = "no_handphone")
    var nohandphone: String,

    @Column(name = "foto_profil")
    var fotoprofil: String? = null,

    @Column(name = "foto_kendaraan")
    var fotokendaraan: String? = null,

    @Column(name = "created_at")
    var createdAt: Date,

    @Column(name = "updated_at")
    var updatedAt: Date?,

    @OneToOne
    @JoinColumn(name = "id_user", unique = true)
    val user: User,

    @ManyToOne
    @JoinColumn(name = "id_grup", nullable = false)
    var group: Group,  // Banyak Uprofile bisa memiliki satu Group

    @OneToMany(mappedBy = "uprofiles", fetch = FetchType.LAZY)
    val orders: List<Orders> = mutableListOf()

//    @OneToOne
//    @JoinColumn(name = "id_user")
//    var user: User
//
//    @OneToMany(mappedBy = "uprofile", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    val orders: List<Orders> = mutableListOf()
    
)
