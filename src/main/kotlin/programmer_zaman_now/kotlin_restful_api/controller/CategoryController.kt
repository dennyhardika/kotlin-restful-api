package programmer_zaman_now.kotlin_restful_api.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import programmer_zaman_now.kotlin_restful_api.model.category.ListCategoryRequest
import programmer_zaman_now.kotlin_restful_api.model.category.CategoryResponse
import programmer_zaman_now.kotlin_restful_api.model.WebResponse
import programmer_zaman_now.kotlin_restful_api.model.category.CreateCategoryRequest
import programmer_zaman_now.kotlin_restful_api.model.category.UpdateCategoryRequest
import programmer_zaman_now.kotlin_restful_api.service.CategoryService

@RestController
class CategoryController(val categoryService: CategoryService) {

    @PostMapping(
        value = ["/api/categories"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createCategory(@RequestBody body: CreateCategoryRequest): WebResponse<CategoryResponse> {
        val categoryResponse = categoryService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = categoryResponse
        )
    }

    @GetMapping(
        value = ["/api/categories/name/{namaktg}"],
        produces = ["application/json"]
    )
    fun getCategoryName(@PathVariable("namaktg") namaktg: String): WebResponse<CategoryResponse> {
        val categoryResponse = categoryService.getnamaktg(namaktg)
        return WebResponse(
            code = 200,
            status = "OK",
            data = categoryResponse
        )
    }

    @GetMapping(
        value = ["/api/categories/id/{idCategory}"],
        produces = ["application/json"]
    )
    fun getCategory(@PathVariable("idCategory") id: Long): WebResponse<CategoryResponse> {
        val categoryResponse = categoryService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = categoryResponse
        )
    }

    @PutMapping(
        value = ["api/categories/{idCategory}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateCategory(@PathVariable("idCategory") id: Long,
                      @RequestBody updateCategoryWithProductsRequest: UpdateCategoryRequest
    ): WebResponse<CategoryResponse> {
        val categoryResponse = categoryService.update(id, updateCategoryWithProductsRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = categoryResponse
        )
    }



    @DeleteMapping(
        value = ["/api/categories/{idCategory}"],
        produces = ["application/json"]
    )
    fun deleteCategory(@PathVariable("idCategory") id: Long): WebResponse<String>{
        val hapus =  categoryService.delete(id).toString()
        return WebResponse(
            code = 200,
            status = "OK",
            data = hapus
        )
    }

    @GetMapping(
        value = ["/api/categories"],
        produces = ["application/json"]
    )
    fun listCategories(@RequestParam(value = "size", defaultValue = "10") size:Int,
                     @RequestParam(value = "page", defaultValue = "0")page:Int): WebResponse<List<CategoryResponse>> {
        val request = ListCategoryRequest(page = page, size = size)
        val responses = categoryService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = responses
        )

    }
}