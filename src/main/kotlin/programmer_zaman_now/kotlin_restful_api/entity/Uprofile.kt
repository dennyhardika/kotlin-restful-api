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
import java.util.Date

@Entity
@Table(name = "Uprofiles")
class Uprofile (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_uprofile")
    val id_uprofile: Long? = null,

    @Column(name = "nama_lengkap")
    var nama_lengkap: String,

    @Column(name = "jenis_kendaraan")
    var jenis_kendaraan: String,

    @Column(name = "alamat")
    var alamat: String,

    @Column(name = "no_handphone")
    var no_handphone: String,

    @Column(name = "foto_profil")
    var foto_profil: String,

    @Column(name = "foto_kendaraan")
    var foto_kendaraan: String,

    @Column(name = "created_at")
    var createdAt: Date,

    @Column(name = "updated_at")
    var updatedAt: Date?,

    @OneToOne
    @JoinColumn(name = "id_user", unique = true)
    val user: User,

    @OneToMany(mappedBy = "uprofiles", fetch = FetchType.LAZY)
    val orders: List<Orders> = mutableListOf()

//    @OneToOne
//    @JoinColumn(name = "id_user")
//    var user: User
//
//    @OneToMany(mappedBy = "uprofile", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    val orders: List<Orders> = mutableListOf()
    
)