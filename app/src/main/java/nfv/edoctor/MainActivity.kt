package nfv.edoctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import nfv.home.LockScreen
import nfv.home.LoginScreen
import nfv.home.OnBoardScreen
import nfv.home.RegisterScreen
import nfv.home.TestResultsScreen
import nfv.home.download.AndroidDownloader
import nfv.home.presentation.HomeScreen
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
                            onClickNext = {
                                navController.navigate(ScreenLogin)
                            }
                        )
                    }
                    composable<ScreenLogin> {
                        LoginScreen(
                            onClickGoogleLogin = {
                                navController.navigate(ScreenHome(name = "Natigue"))
                            },
                            onClickRegister = {
                                navController.navigate(ScreenRegister)
                            }
                        )
                    }
                    composable<ScreenRegister> {
                        RegisterScreen()
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
                        val context = LocalContext.current
                        val downloader = remember { AndroidDownloader(context) }

                        TestResultsScreen(
                            onClickBack = {
                                navController.popBackStack()
                            },
                            onClickDownloadDocument = {
                                downloader.downloadFile(
                                    url = "https://pl-coding.com/wp-content/uploads/2022/04/pic-squared.jpg",
                                    titleAppendix = it
                                )
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
object ScreenLogin

@Serializable
object ScreenRegister

@Serializable
object ScreenHistory

@Serializable
object ScreenProfile

@Serializable
object ScreenLock