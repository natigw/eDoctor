package nfv.edoctor

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import nfv.auth.presentation.navigation.authNavigation
import nfv.consultation.navigation.consultationNavigation
import nfv.history.presentation.navigation.historyNavigation
import nfv.home.navigation.homeNavigation
import nfv.map.presentation.navigation.mapNavigation
import nfv.navigation.di.Navigator
import nfv.navigation.routes.AuthNavigation
import nfv.profile.navigation.profileNavigation
import nfv.storage.local.domain.UserPreferencesStorage
import nfv.storage.local.model.SupportedThemes
import nfv.ui_kit.theme.EDoctorTheme
import nfv.ui_kit.theme.Primary500
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var userPreferencesStorage: UserPreferencesStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            val allowScreenShots = userPreferencesStorage.getScreenshotsAllowedStatus().collectAsState(initial = false)
            val currentTheme = userPreferencesStorage.getCurrentTheme().collectAsState(initial = SupportedThemes.DARK)

            val navController = rememberNavController()
            val systemUiController = rememberSystemUiController()

            val systemBarsColor = Primary500
            val navHostBackgroundColor = MaterialTheme.colorScheme.background

            LaunchedEffect(allowScreenShots.value) {
                if (!allowScreenShots.value)
                    window.setFlags(
                        WindowManager.LayoutParams.FLAG_SECURE,
                        WindowManager.LayoutParams.FLAG_SECURE
                    )
                else
                    window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
            }

            LaunchedEffect(Unit) {
                navigator.observe(this) { onCommand ->
                    onCommand(navController)
                }
            }

            SideEffect {
                systemUiController.setSystemBarsColor(color = systemBarsColor)
            }

            EDoctorTheme(darkTheme = currentTheme.value == SupportedThemes.DARK) {

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
                    mapNavigation()
                    consultationNavigation()
                    historyNavigation()
                    profileNavigation()
                }
            }
        }
    }
}