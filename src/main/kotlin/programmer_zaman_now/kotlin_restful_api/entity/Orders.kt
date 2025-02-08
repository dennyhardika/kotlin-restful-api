package programmer_zaman_now.kotlin_restful_api.entity

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
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "Orders")
class Orders (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    val id_order: Long? = null,

    @Column(name = "created_at")
    var createdAt: Date,

    @Column(name = "updated_at")
    var updatedAt: Date?,

    @Column(name = "started_at")
    var startedAt: Date?,

    @Column(name = "finished_at")
    var finishedAt: Date?,

    @Column(name = "keterangan")
    var keterangan: String,

    @Column(name = "foto_one")
    var foto_one: String,

    @Column(name = "foto_two")
    var foto_two: String,

    @Column(name = "status")
    var status: String,

    @ManyToOne
    @JoinColumn(name = "id_uprofile")
    val uprofiles: Uprofile,

    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = [JoinColumn(name = "id_order")],
        inverseJoinColumns = [JoinColumn(name = "id_produk")]
    )
    var products: MutableList<Product> = mutableListOf()

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