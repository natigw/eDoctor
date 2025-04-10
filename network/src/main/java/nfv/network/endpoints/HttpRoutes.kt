package nfv.network.endpoints

object HttpRoutes {

    private const val BASE_URL = "http://13.49.0.170:8080"

    const val REGISTER_MAIL = "$BASE_URL/register/mail"
    const val LOGIN_MAIL = "$BASE_URL/login/mail"

    const val NEWS = "$BASE_URL/news"

    const val CONSULTATION = "$BASE_URL/ai/consultation"

    const val PHARMACIES = "$BASE_URL/location/pharmacy"

    const val TEST_RESULTS = "$BASE_URL/test-history"

}