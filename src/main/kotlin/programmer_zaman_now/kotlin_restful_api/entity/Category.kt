package programmer_zaman_now.kotlin_restful_api.entity

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
@Table(name = "Categories")
class Category (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kategori")
    val idkategori: Long? = null,

    @Column(name = "nama_kategori")
    var namakategori: String,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    @Column(name = "updated_at")
    var updatedAt: Date? = null,

    @OneToMany(mappedBy = "categories", orphanRemoval = true, cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var products: MutableList<Product> = mutableListOf()

//    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    val products: List<Product> = mutableListOf()

    )
