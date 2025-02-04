package programmer_zaman_now.kotlin_restful_api.controller

import jakarta.validation.ConstraintViolationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import programmer_zaman_now.kotlin_restful_api.error.NotFoundExpection
import programmer_zaman_now.kotlin_restful_api.model.WebResponse

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHalder(constraintViolationException: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = constraintViolationException.message!!
        )

    }

    @ExceptionHandler(value = [NotFoundExpection::class])
    fun notFound(notFoundExpection: NotFoundExpection): WebResponse<String> {
        return WebResponse(
            code = 404,
            status = "NOT FOUND",
            data = "Not Found"
        )
    }

}