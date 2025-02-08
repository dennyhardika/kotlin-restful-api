package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Brand
import programmer_zaman_now.kotlin_restful_api.entity.orders.Packet
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.BrandResponse
import programmer_zaman_now.kotlin_restful_api.model.packet.CreatePacketRequest
import programmer_zaman_now.kotlin_restful_api.model.packet.ListPacketRequest
import programmer_zaman_now.kotlin_restful_api.model.packet.PacketResponse
import programmer_zaman_now.kotlin_restful_api.model.packet.UpdatePacketRequest
import programmer_zaman_now.kotlin_restful_api.repository.PacketRepository
import programmer_zaman_now.kotlin_restful_api.service.PacketService
import java.util.Date
import java.util.stream.Collectors

@Service
class PacketServiceImpl(val packetRepository: PacketRepository): PacketService {
    override fun create(createPacketRequest: CreatePacketRequest): PacketResponse {
        val paket = Packet(
            namapaket = createPacketRequest.namapaket!!,
            kethargapaket = createPacketRequest.kethargapaket!!,
            createdAt = Date(),
            updatedAt = null,
            namakategori = createPacketRequest.namakategori!!,
            produk1 = createPacketRequest.produk1!!,
            produk2 = createPacketRequest.produk2 ?: "",
            produk3 = createPacketRequest.produk3 ?: "",
            produk4 = createPacketRequest.produk4 ?: ""
        )
        packetRepository.save(paket)
        return convertPacketToPacketResponse(paket)
    }

    override fun get(id: Long): PacketResponse {
        val paket = findPacketByOrThrowNotFound(id)
        return convertPacketToPacketResponse(paket)
    }

    override fun getnamapkt(namapaket: String): PacketResponse {
        println("Mencari paket dengan nama: $namapaket") // Debugging
        val paket = findPacketNameByOrThrowNotFound(namapaket)
        println("paket ditemukan: ${paket.namapaket}") // Debugging
        return convertPacketToPacketResponse(paket)
    }

    override fun update(id: Long, updatePacketRequest: UpdatePacketRequest): PacketResponse {
        val paket = findPacketByOrThrowNotFound(id)

        paket.apply {
            namapaket = updatePacketRequest.namapaket
            kethargapaket = updatePacketRequest.kethargapaket
            updatedAt = Date()
            namakategori = updatePacketRequest.namakategori
            produk1 = updatePacketRequest.produk1
            produk2 = updatePacketRequest.produk2 ?: ""
            produk3 = updatePacketRequest.produk3 ?: ""
            produk4 = updatePacketRequest.produk4 ?: ""
        }

        packetRepository.save(paket)

        return convertPacketToPacketResponse(paket)
    }

    override fun delete(id: Long) {
        val paket = findPacketByOrThrowNotFound(id)
        packetRepository.delete(paket)
    }

    override fun list(listPacketRequest: ListPacketRequest): List<PacketResponse> {
        val page = packetRepository.findAll(PageRequest.of(listPacketRequest.page, listPacketRequest.size))
        val packets: List<Packet> = page.get().collect(Collectors.toList())

        return packets.map { convertPacketToPacketResponse(it) }
    }

    private fun findPacketByOrThrowNotFound(id: Long): Packet =
        packetRepository.findByIdpaket(id) ?: throw NotFoundExpection()

    private fun findPacketNameByOrThrowNotFound(namapaket: String): Packet {
        return packetRepository.findByNamaPaket(namapaket)
            ?: throw NotFoundExpection()
    }

    private fun convertPacketToPacketResponse(packet: Packet): PacketResponse {
        return PacketResponse(
            idpaket = packet.idpaket!!,
            namapaket = packet.namapaket,
            kethargapaket = packet.kethargapaket,
            createdAt = packet.createdAt,
            updatedAt = packet.updatedAt,
            namakategori = packet.namakategori,
            produk1 = packet.produk1,
            produk2 = packet.produk2 ?: "",
            produk3 = packet.produk3 ?: "",
            produk4 = packet.produk4 ?: ""
        )
    }
}