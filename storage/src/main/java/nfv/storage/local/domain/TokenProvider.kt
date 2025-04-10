package nfv.storage.local.domain

interface TokenProvider {
    suspend fun getToken(): String?
}
