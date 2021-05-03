import java.lang.IllegalStateException

interface HeadersChain {
    fun addHeader(inputHeader: String): String
}

class AuthenticationHeader(val token: String?, var next: HeadersChain? = null): HeadersChain {
    override fun addHeader(inputHeader: String): String {
        token ?: throw IllegalStateException("Null token")
        return inputHeader + "Authorization: Bearer $token\n"
            .let { next?.addHeader(it) ?: it }
    }
}

class ContentTypeHeader(val contentType: String, var next: HeadersChain? = null): HeadersChain {
    override fun addHeader(inputHeader: String): String =
        inputHeader + "Content Type: $contentType\n"
            .let { next?.addHeader(it) ?: it }
}

class BodyPayload(val body: String, var next: HeadersChain? = null): HeadersChain {
    override fun addHeader(inputHeader: String): String =
        inputHeader + "Body: $body\n"
            .let { next?.addHeader(it) ?: it }
}

fun main() {
    val authenticationHeader = AuthenticationHeader("3429587329578")
    val contentTypeHeader = ContentTypeHeader("json")
    val bodyPayload = BodyPayload("{ \"ping\": \"pong\" }")

    authenticationHeader.next = contentTypeHeader
    contentTypeHeader.next = bodyPayload

    val msgWithAuth = authenticationHeader.addHeader("Header with authentication\n")
    println(msgWithAuth)

    val msgWithoutAuth = contentTypeHeader.addHeader("Header without authentication\n")
    println(msgWithoutAuth)
}
