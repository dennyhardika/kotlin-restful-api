package programmer_zaman_now.kotlin_restful_api.entity.orders

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import programmer_zaman_now.kotlin_restful_api.entity.Orders
import programmer_zaman_now.kotlin_restful_api.entity.User
import java.util.Date

@Entity
@Table(name = "Ordersfd")
class Ordersfd (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orderfd")
    val idorderfd: Long? = null,

    @Column(name = "waktu_mulai")
    var waktumulai: String,

    @Column(name = "waktu_selesai")
    var waktuselesai: String,

    @Column(name = "foto_onefd", nullable = true)
    var foto_onefd: String? = null,

    @Column(name = "foto_twofd", nullable = true)
    var foto_twofd: String? = null,

    @Column(name = "foto_threefd", nullable = true)
    var foto_threefd: String? = null,

    @Column(name = "foto_fourfd", nullable = true)
    var foto_fourfd: String? = null,

    @Column(name = "keterangan", nullable = true)
    var keteranganfd: String? = null,

    @Column(name = "created_at")
    var createdAt: Date,

    @Column(name = "updated_at")
    var updatedAt: Date?,

    @OneToOne
    @JoinColumn(name = "id_order", unique = true)
    val orders: Orders,

    )
