package programmer_zaman_now.kotlin_restful_api.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import programmer_zaman_now.kotlin_restful_api.entity.orders.Packet
import programmer_zaman_now.kotlin_restful_api.entity.orders.Promo
import java.util.Date

@Entity
@Table(name = "Orders")
class Orders(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    val id_order: Long? = null,

    @Column(name = "icon_order")
    var iconorder: String,

    @Column(name = "kategori1")
    var kategori1: String? = null,

    @Column(name = "produk1a")
    var produk1a: String? = null,

    @Column(name = "produk1b")
    var produk1b: String? = null,

    @Column(name = "produk1c")
    var produk1c: String? = null,

    @Column(name = "produk1d")
    var produk1d: String? = null,

    @Column(name = "kategori2")
    var kategori2: String? = null,

    @Column(name = "produk2a")
    var produk2a: String? = null,

    @Column(name = "produk2b")
    var produk2b: String? = null,

    @Column(name = "produk2c")
    var produk2c: String? = null,

    @Column(name = "produk2d")
    var produk2d: String? = null,

    @Column(name = "tanggal_kedatangan")
    var tanggalkedatangan: String,

    @Column(name = "keterangan", nullable = true)
    var keterangan: String? = null,

    @Column(name = "foto_one", nullable = true)
    var foto_one: String? = null,

    @Column(name = "foto_two", nullable = true)
    var foto_two: String? = null,

    @Column(name = "status_booking")
    var statusbooking: String,

    @Column(name = "tipe_booking")
    var tipebooking: String,

    @Column(name = "nama_booking")
    var namabooking: String,

    @ManyToOne
    @JoinColumn(name = "id_uprofile", nullable = false)
    val uprofiles: Uprofile,

    @Column(name = "created_at")
    var createdAt: Date,

    @Column(name = "updated_at")
    var updatedAt: Date?


    //    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    var packets: MutableList<Packet> = mutableListOf(),  // **Ubah dari ManyToMany menjadi OneToMany**
//
//    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    var promotions: MutableList<Promo> = mutableListOf()  // **Ubah dari ManyToMany menjadi OneToMany**

//
//    @ManyToMany
//    @JoinTable(
//        name = "order_products",
//        joinColumns = [JoinColumn(name = "id_order")],
//        inverseJoinColumns = [JoinColumn(name = "id_produk")]
//    )
//    var products: MutableList<Product> = mutableListOf()

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_uprofile", nullable = false)
//    val uprofile: Uprofile,

//    @ManyToMany
//    @JoinTable(
//        name = "orders",
//        joinColumns = [JoinColumn(name = "id_order")],
//        inverseJoinColumns = [JoinColumn(name = "id_produk")]
//    )
//    val product: List<Product> = mutableListOf(),

    )
