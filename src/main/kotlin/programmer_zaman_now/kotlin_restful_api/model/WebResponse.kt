package programmer_zaman_now.kotlin_restful_api.model

data class WebResponse<T> (

    val code: Int,

    val status: String,

    val data: T
)