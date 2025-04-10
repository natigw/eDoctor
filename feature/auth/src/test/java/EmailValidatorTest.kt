import nfv.auth.domain.usecase.EmailValidatorUseCase
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmailValidatorTest {

    private val emailValidator = EmailValidatorUseCase()

    @Test
    fun emailValidator_validEmail_ReturnsTrue() {
        val validEmail = "test@example.com"
        val result = emailValidator.execute(validEmail)
        assertTrue(result)
    }

    @Test
    fun emailValidator_invalidEmailNoAtSymbol_ReturnsFalse() {
        val invalidEmail = "testexample.com"
        val result = emailValidator.execute(invalidEmail)
        assertFalse(result)
    }

    @Test
    fun emailValidator_invalidEmailNoDomain_ReturnsFalse() {
        val invalidEmail = "test@.com"
        val result = emailValidator.execute(invalidEmail)
        assertFalse(result)
    }

    @Test
    fun emailValidator_invalidEmailNoTLD_ReturnsFalse() {
        val invalidEmail = "test@example"
        val result = emailValidator.execute(invalidEmail)
        assertFalse(result)
    }

    @Test
    fun emailValidator_emailWithSpecialChars_ReturnsTrue() {
        val validEmail = "test+user@example.com"
        val result = emailValidator.execute(validEmail)
        assertTrue(result)
    }

    @Test
    fun emailValidator_emptyEmail_ReturnsFalse() {
        val emptyEmail = ""
        val result = emailValidator.execute(emptyEmail)
        assertFalse(result)
    }
}