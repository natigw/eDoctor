package nfv.auth.domain.usecase

import javax.inject.Inject

class EmailValidatorUseCase @Inject constructor() {

    fun execute(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return email.matches(emailRegex)
    }

}