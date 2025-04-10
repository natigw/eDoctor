package nfv.auth.domain.repository

import nfv.auth.domain.model.AuthenticationMailModel

interface AuthRepository {

    suspend fun registerWithEmail(email: String)

    suspend fun registerVerifyOtp(email: String, otp: String, password: String) : AuthenticationMailModel?

    suspend fun loginWithEmail(email: String, password: String) : AuthenticationMailModel?

}