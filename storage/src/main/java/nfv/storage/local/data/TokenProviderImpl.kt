package nfv.storage.local.data

import kotlinx.coroutines.flow.firstOrNull
import nfv.storage.local.domain.TokenProvider
import nfv.storage.local.domain.TokenStorage
import javax.inject.Inject

class TokenProviderImpl @Inject constructor(
    private val tokenStorage: TokenStorage
) : TokenProvider {

    override suspend fun getToken(): String? {
        return tokenStorage.getToken().firstOrNull()
    }

}
