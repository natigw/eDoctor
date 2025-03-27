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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import nfv.auth.navigation.authNavigation
import nfv.history.navigation.historyNavigation
import nfv.home.navigation.homeNavigation
import nfv.navigation.di.Navigator
import nfv.navigation.routes.HomeNavigation
import nfv.profile.navigation.profileNavigation
import nfv.ui_kit.theme.EDoctorTheme
import nfv.ui_kit.theme.Primary500
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    startDestination = HomeNavigation//AuthNavigation
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