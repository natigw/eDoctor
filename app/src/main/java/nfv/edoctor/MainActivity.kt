package nfv.edoctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import nfv.home.LockScreen
import nfv.home.OnBoardScreen
import nfv.home.TestResultsScreen
import nfv.home.presentation.HomeScreen
import nfv.ui_kit.components.buttons.ButtonState
import nfv.ui_kit.theme.EDoctorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
//        WindowCompat.setDecorFitsSystemWindows(window, false)  //TODO -> bu neye lazimdi
        setContent {
            EDoctorTheme {

                val navController = rememberNavController()
//                val focusManager = LocalFocusManager.current   //TODO -> bu neye lazimdi

                NavHost(
//                    modifier = Modifier  //TODO -> bu neye lazimdi
//                        .fillMaxSize()
//                        .background(color = Primary500)
//                        .clickable(indication = null, interactionSource = null) {
//                            focusManager.clearFocus()
//                        },
                    navController = navController,
                    startDestination = ScreenOnBoard
                ) {
                    composable<ScreenOnBoard> {
                        OnBoardScreen(
                            onClickNext = { buttonState: ButtonState ->
                                if (buttonState == ButtonState.ENABLED) {
                                    navController.navigate(ScreenHome(name = "Natigue"))
                                }
                            }
                        )
                    }

                    composable<ScreenHome> {
                        val args = it.toRoute<ScreenHome>()
                        HomeScreen(
                            username = args.name,
                            onClickHome = {

                            },
                            onClickHistory = {
                                navController.navigate(ScreenHistory)
                            },
                            onClickProfile = {
                                navController.navigate(ScreenLock)
                            }
                        )
                    }

                    composable<ScreenHistory> {
                        TestResultsScreen(
                            onClickBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable<ScreenLock> {
                        LockScreen()
                    }
                }
            }
        }
    }
}

@Serializable
object ScreenOnBoard

@Serializable
data class ScreenHome(          //diqqet: bu data class oldu, yuxaridaki ise object!!!
    val name: String //argument
)

@Serializable
object ScreenHistory

@Serializable
object ScreenProfile

@Serializable
object ScreenLock