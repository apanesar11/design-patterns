sealed class AuthorizationState

object Unauthorized:  AuthorizationState()

class Authorized(val userName: String): AuthorizationState()

class AuthorizationPresenter {

    private var state: AuthorizationState = Unauthorized;

    val isAuthorized: Boolean
        get() = when(state) {
            is Authorized -> true
            is Unauthorized -> false
        }

    val userName: String
        get() {
            val state = this.state;
            return when(state) {
                is Authorized -> state.userName
                is Unauthorized -> "unknown"
            }
        }

    fun loginUser(userName: String) {
        state = Authorized(userName)
    }

    fun logoutUser() {
        state = Unauthorized
    }

    override fun toString() = "Authorization is $isAuthorized and user is $userName"
}

fun main() {
    val authorizationPresenter = AuthorizationPresenter()

    authorizationPresenter.loginUser("apanesar11")
    println(authorizationPresenter)

    authorizationPresenter.logoutUser()
    println(authorizationPresenter)
}