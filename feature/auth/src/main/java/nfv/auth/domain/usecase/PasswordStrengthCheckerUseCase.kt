package nfv.auth.domain.usecase

import nfv.ui_kit.components.inputFields.PasswordStrength
import javax.inject.Inject

class PasswordStrengthCheckerUseCase @Inject constructor() {

    fun execute(password: String): PasswordStrength {
        val passwordLength = password.length
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }

        return if (password.isBlank() || passwordLength < 4)
            PasswordStrength.NONE
        else if (passwordLength < 6 || !hasLowerCase || !hasUpperCase || !hasDigit) {
            PasswordStrength.WEAK
        } else if (!hasSpecialChar) {
            PasswordStrength.MEDIUM
        } else PasswordStrength.STRONG
    }

}