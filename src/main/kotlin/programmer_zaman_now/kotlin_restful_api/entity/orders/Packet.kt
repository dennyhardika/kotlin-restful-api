package programmer_zaman_now.kotlin_restful_api.entity.orders

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import programmer_zaman_now.kotlin_restful_api.entity.Orders
import java.util.Date

@Entity
@Table(name = "packages")
class Packet (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpaket", nullable = false)
    val idpaket: Long? = null,

    @Column(name = "nama_paket")
    var namapaket: String,

    @Column(name = "nama_kategori")
    var namakategori: String,

    @Column(name = "produk_1")
    var produk1: String,

    @Column(name = "produk_2")
    var produk2: String? = null,

    @Column(name = "produk_3")
    var produk3: String? = null,

    @Column(name = "produk_4")
    var produk4: String? = null,

    @Column(name = "ket_hargapaket")
    var kethargapaket: String,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    @Column(name = "updated_at")
    var updatedAt: Date? = null

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_paket", nullable = false)  // **Tambahkan foreign key ke Orders**
//    var order: Orders

)