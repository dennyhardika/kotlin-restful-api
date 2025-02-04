package programmer_zaman_now.kotlin_restful_api.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "products")
class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id_produk: Long? = null,

    @Column(name = "nama_produk")
    var nama_produk: String,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    @Column(name = "updated_at")
    var updatedAt: Date? = null,

    @ManyToOne
    @JoinColumn(name = "id_kategori", nullable = false)
    var categories: Category,

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    val orders: List<Orders> = mutableListOf()

//    @ManyToOne
//    @JoinColumn(name = "id_kategori", nullable = false)
//    var category: Category,
//
//    @ManyToMany(mappedBy = "product", fetch = FetchType.LAZY)
//    val orders: List<Orders> = mutableListOf()
)