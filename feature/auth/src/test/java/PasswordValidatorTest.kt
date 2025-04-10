import nfv.auth.domain.usecase.PasswordStrengthCheckerUseCase
import nfv.ui_kit.components.inputFields.PasswordStrength
import org.junit.Assert.assertTrue
import org.junit.Test

class PasswordValidatorTest {

    private val passwordStrengthChecker = PasswordStrengthCheckerUseCase()

    @Test
    fun passwordChecker_blank_ReturnsNone() {
        val expectedResult = PasswordStrength.NONE
        val actualResult = passwordStrengthChecker.execute("")
        assertTrue(expectedResult == actualResult)
    }

    @Test
    fun passwordChecker_shortLength_ReturnsNone() {
        val expectedResult = PasswordStrength.NONE
        val actualResult = passwordStrengthChecker.execute("abc")
        assertTrue(expectedResult == actualResult)
    }

    @Test
    fun passwordChecker_weakPassword_ReturnsWeak() {
        val expectedResult = PasswordStrength.WEAK
        val actualResult = passwordStrengthChecker.execute("abc123")
        assertTrue(expectedResult == actualResult)
    }

    @Test
    fun passwordChecker_mediumPassword_ReturnsMedium() {
        val expectedResult = PasswordStrength.MEDIUM
        val actualResult = passwordStrengthChecker.execute("Abc123")
        assertTrue(expectedResult == actualResult)
    }

    @Test
    fun passwordChecker_strongPassword_ReturnsStrong() {
        val expectedResult = PasswordStrength.STRONG
        val actualResult = passwordStrengthChecker.execute("Abc@1234")
        assertTrue(expectedResult == actualResult)
    }

    @Test
    fun passwordChecker_noSpecialChar_ReturnsMedium() {
        val expectedResult = PasswordStrength.MEDIUM
        val actualResult = passwordStrengthChecker.execute("Abc1234")
        assertTrue(expectedResult == actualResult)
    }
}
