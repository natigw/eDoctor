package nfv.edoctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.launch
import nfv.auth.presentation.navigation.authNavigation
import nfv.history.navigation.historyNavigation
import nfv.home.navigation.homeNavigation
import nfv.navigation.di.Navigator
import nfv.navigation.routes.AuthNavigation
import nfv.profile.navigation.profileNavigation
import nfv.ui_kit.theme.EDoctorTheme
import nfv.ui_kit.theme.Primary500
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var ktorClient: HttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            ktorClient.get(
                "http://192.168.2.6:8080/location/pharmacy",
            ) {
                header(
                    "Authorization",
                    "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6IkVEb2N0b3IiLCJlbWFpbCI6Im5hdGlxQGdtYWlsLmNvIiwiZXhwIjoxNzQzMjIyNDEwfQ.Q44sdwFgpC3srTL9ukl67rSj9C3SunaNJKyKeEHB7N_atHT6DPxuIAF5Q0HsZ110_qdyirSvYRYEW2KOHrqAzw"
                )
                header(
                    "Accept-Language",
                    "ru"
                )
            }
        }

        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val systemUiController = rememberSystemUiController()

            val systemBarsColor = Primary500
            val navHostBackgroundColor = MaterialTheme.colorScheme.background

            LaunchedEffect(Unit) {
                navigator.observe(this) { onCommand ->
                    onCommand(navController)
                }
            }

            SideEffect {
                systemUiController.setSystemBarsColor(color = systemBarsColor)
            }

            EDoctorTheme(darkTheme = false) {

                val focusManager = LocalFocusManager.current
                NavHost(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = navHostBackgroundColor)
                        .clickable(
                            indication = null,
                            interactionSource = null,
                            onClick = {
                                focusManager.clearFocus()
                            }
                        ),
                    navController = navController,
                    startDestination = AuthNavigation
                ) {
                    authNavigation()
                    homeNavigation()
                    historyNavigation()
                    profileNavigation()
                }
            }
        }
    }
}