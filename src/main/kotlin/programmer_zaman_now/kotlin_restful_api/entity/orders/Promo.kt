package programmer_zaman_now.kotlin_restful_api.entity.orders

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import programmer_zaman_now.kotlin_restful_api.entity.Category
import programmer_zaman_now.kotlin_restful_api.entity.Orders
import programmer_zaman_now.kotlin_restful_api.entity.Product
import java.util.Date

@Entity
@Table(name = "promotions")
class Promo (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpromo")
    val idpromo: Long? = null,

    @Column(name = "nama_promo")
    var namapromo: String,

    @Column(name = "ket_hargapromo")
    var kethargapromo: String,

    @Column(name = "keterangan_promo")
    var ketpromo: String,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    @Column(name = "updated_at")
    var updatedAt: Date? = null,

    @Column(name = "nama_kategori")
    var namakategori: String,

    @Column(name = "exp_promo")
    var exppromo: String

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_order", nullable = false)  // **Tambahkan foreign key ke Orders**
//    var order: Orders,

//    @OneToOne
//    @JoinColumn(name = "id_kategori", nullable = false, unique = true) // Foreign key ke Category
//    val category: Category

//    @ManyToMany
//    @JoinTable(
//        name = "promotion_products",
//        joinColumns = [JoinColumn(name = "id_promo")],
//        inverseJoinColumns = [JoinColumn(name = "id_produk")]
//    )
//    var products: MutableList<Product> = mutableListOf()

)