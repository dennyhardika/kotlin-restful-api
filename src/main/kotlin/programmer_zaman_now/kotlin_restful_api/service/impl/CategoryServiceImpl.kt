package programmer_zaman_now.kotlin_restful_api.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import programmer_zaman_now.kotlin_restful_api.entity.Category
import programmer_zaman_now.kotlin_restful_api.entity.User
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.category.CategoryResponse
import programmer_zaman_now.kotlin_restful_api.model.category.CreateCategoryRequest
import programmer_zaman_now.kotlin_restful_api.model.category.ListCategoryRequest
import programmer_zaman_now.kotlin_restful_api.model.category.UpdateCategoryRequest
import programmer_zaman_now.kotlin_restful_api.model.user.UserResponse
import programmer_zaman_now.kotlin_restful_api.repository.CategoryRepository
import programmer_zaman_now.kotlin_restful_api.service.CategoryService
import java.util.Date
import java.util.stream.Collectors

@Service
class CategoryServiceImpl(val categoryRepository: CategoryRepository): CategoryService {
    override fun create(createCategoryRequest: CreateCategoryRequest): CategoryResponse {
        val category = Category(
            namakategori = createCategoryRequest.namakategori!!,
            createdAt = Date(),
            updatedAt = null
        )
        categoryRepository.save(category)
        return convertCategoryToCategoryResponse(category)
    }

    override fun get(id: Long): CategoryResponse {
        val category = findCategoryByOrThrowNotFound(id)
        return convertCategoryToCategoryResponse(category)
    }

    override fun getnamaktg(namakategori: String): CategoryResponse {
        val category = findCategoryNameByOrThrowNotFound(namakategori)
        return convertCategoryToCategoryResponse(category)
    }

    override fun update(id: Long, updateCategoryRequest: UpdateCategoryRequest): CategoryResponse {
        val category = findCategoryByOrThrowNotFound(id)

        category.apply {
            namakategori = updateCategoryRequest.namakategori!!
            updatedAt = Date()
        }

        categoryRepository.save(category)

        return convertCategoryToCategoryResponse(category)
    }

    override fun delete(id: Long) {
        val category = findCategoryByOrThrowNotFound(id)
        categoryRepository.delete(category)
    }

    override fun list(listCategoryRequest: ListCategoryRequest): List<CategoryResponse> {
        val page = categoryRepository.findAll(PageRequest.of(listCategoryRequest.page, listCategoryRequest.size))
        val categories: List<Category> = page.get().collect(Collectors.toList())

        return categories.map { convertCategoryToCategoryResponse(it) }
    }

    private fun findCategoryByOrThrowNotFound(id: Long): Category =
    categoryRepository.findByIdkategori(id) ?: throw NotFoundExpection()

    private fun findCategoryNameByOrThrowNotFound(namakategori: String): Category {
        return categoryRepository.findByNamakategori(namakategori) ?: throw NotFoundExpection()
    }

    private fun convertCategoryToCategoryResponse(category: Category): CategoryResponse {
        return CategoryResponse(
            idkategori = category.idkategori!!,
            namakategori = category.namakategori,
            createdAt = category.createdAt,
            updatedAt = category.updatedAt
        )
    }
}
