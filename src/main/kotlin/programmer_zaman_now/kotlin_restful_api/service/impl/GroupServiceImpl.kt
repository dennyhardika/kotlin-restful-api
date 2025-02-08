package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import programmer_zaman_now.kotlin_restful_api.entity.Uprofile
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Brand
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.Group
import programmer_zaman_now.kotlin_restful_api.entity.kendaraan.VehicleType
import programmer_zaman_now.kotlin_restful_api.error.NotFoundException
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.brand.BrandResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.group.CreateGroupRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.group.GroupResponse
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.group.ListGroupRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.group.UpdateGroupRequest
import programmer_zaman_now.kotlin_restful_api.model.kendaraan.type.TypeResponse
import programmer_zaman_now.kotlin_restful_api.repository.GroupRepository
import programmer_zaman_now.kotlin_restful_api.service.GroupService
import java.util.Date
import java.util.stream.Collectors

@Service
class GroupServiceImpl (val groupRepository: GroupRepository): GroupService{
    override fun create(createGroupRequest: CreateGroupRequest): GroupResponse {
        val group = Group(
            grupkendaraan = createGroupRequest.grupkendaraan!!,
            createdAt = Date(),
            updatedAt = null
        )
        groupRepository.save(group)

        return GroupResponse(
            idgrup = group.idgrup!!,
            grupkendaraan = group.grupkendaraan!!,
            createdAt = group.createdAt,
            updatedAt = group.updatedAt
        )
    }

    override fun get(id: Long): GroupResponse {
        val group = findGroupByOrThrowNotFound(id)
        return convertGroupToGroupResponse(group)
    }

    override fun getgrupknd(grupkendaraan: String): GroupResponse {
        println("Mencari grup dengan nama: $grupkendaraan") // Debugging
        val product = findGroupNameByOrThrowNotFound(grupkendaraan)
        println("grup ditemukan: ${product.grupkendaraan}") // Debugging
        return convertGroupToGroupResponse(product)
    }

    override fun update(id: Long, updateGroupRequest: UpdateGroupRequest): GroupResponse {
        val vehicletype = findGroupByOrThrowNotFound(id)

        vehicletype.apply {
            grupkendaraan = updateGroupRequest.grupkendaraan ?: grupkendaraan
            updatedAt = Date()
        }

        groupRepository.save(vehicletype)

        return convertGroupToGroupResponse(vehicletype)
    }

    override fun delete(id: Long) {
        val group = findGroupByOrThrowNotFound(id)
        groupRepository.delete(group)
    }

    override fun list(listGroupRequest: ListGroupRequest): List<GroupResponse> {
        val page = groupRepository.findAll(PageRequest.of(listGroupRequest.page, listGroupRequest.size))
        val groups: List<Group> = page.get().collect(Collectors.toList())

        return groups.map { convertGroupToGroupResponse(it) }
    }

//    private fun findGroupByOrThrowNotFound(id: Long): Group =
//        groupRepository.findByIdgroup(id) ?: throw NotFoundExpection()

    private fun findGroupByOrThrowNotFound(id: Long): Group {
        val grup = groupRepository.findByIdOrNull(id)
        if (grup == null){
            throw NotFoundExpection()
        }else {
            return grup;
        }
    }

    private fun findGroupNameByOrThrowNotFound(grupkendaraan: String): Group {
        return groupRepository.findByGrupKendaraan(grupkendaraan)
            ?: throw NotFoundExpection()
    }

    private fun convertGroupToGroupResponse(group: Group): GroupResponse {
        return GroupResponse(
            idgrup = group.idgrup!!,
            grupkendaraan = group.grupkendaraan,
            createdAt = group.createdAt,
            updatedAt = group.updatedAt
        )
    }
}