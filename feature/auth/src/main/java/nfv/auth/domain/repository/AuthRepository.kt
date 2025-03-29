package nfv.auth.domain.repository

import nfv.auth.domain.model.RegisterMailModel

interface AuthRepository {

    suspend fun registerWithEmail(email: String, password: String) : RegisterMailModel?

}