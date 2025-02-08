package programmer_zaman_now.kotlin_restful_api.validation

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validator

class ValidationUtil(val validator: Validator) {

    fun validate(any: Any) {
        val result = validator.validate(any)
        if (result.size != 0) {
            throw ConstraintViolationException(result)
        }
    }
}