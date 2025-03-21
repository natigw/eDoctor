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
import nfv.home.ChangePasscodeScreen
import nfv.home.InfoAboutUsScreen
import nfv.home.InfoTermsConditionsScreen
import nfv.home.LockScreen
import nfv.home.LoginScreen
import nfv.home.MedicalInfoScreen
import nfv.home.OnBoardScreen
import nfv.home.ProfileScreen
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
                                navController.navigate(ScreenHome(name = "Natig"))
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
                            onGoToHome = {

                            },
                            onGotoHistory = {
                                navController.navigate(ScreenHistory(isComingFromProfile = false))
                            },
                            onGoToProfile = {
                                navController.navigate(ScreenProfile)
                            }
                        )
                    }

                    composable<ScreenHistory> {
                        val args = it.toRoute<ScreenHistory>()

                        val context = LocalContext.current
                        val downloader = remember { AndroidDownloader(context) }

                        TestResultsScreen(
                            isComingFromProfile = args.isComingFromProfile,
                            onGoToHome = {
                                navController.navigate(ScreenHome(name = "Natihistory"))
                            },
                            onGotoHistory = {

                            },
                            onGoToProfile = {
                                navController.navigate(ScreenProfile)
                            },
                            onClickBack = {
                                navController.popBackStack()
                            },
                            onClickDownloadDocument = { title ->
                                downloader.downloadFile(
                                    url = "https://pl-coding.com/wp-content/uploads/2022/04/pic-squared.jpg",
                                    titleAppendix = title
                                )
                            }
                        )
                    }

                    composable<ScreenProfile> {
                        ProfileScreen(
                            onGoToHome = {
                                navController.navigate(ScreenHome(name = "Natigg"))
                            },
                            onGotoHistory = {
                                navController.navigate(ScreenHistory(isComingFromProfile = false))
                            },
                            onGoToProfile = {

                            },
                            onClickMedicalInfo = {
                                navController.navigate(ScreenMedicalInfo)
                            },
                            onGoToChangePasscode = {
                                navController.navigate(ScreenChangePasscode)
                            },
                            onGoToTermsInfo = {
                                navController.navigate(ScreenTermsInfo)
                            },
                            onGoToAboutUsInfo = {
                                navController.navigate(ScreenAboutUsInfo)
                            }
                        )
                    }

                    composable<ScreenMedicalInfo> {
                        MedicalInfoScreen(
                            onClickBack = {
                                navController.popBackStack()
                            },
                            onClickLabTests = {
                                navController.navigate(ScreenHistory(isComingFromProfile = true))
                            }
                        )
                    }

                    composable<ScreenTermsInfo> {
                        InfoTermsConditionsScreen(
                            onClickBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable<ScreenAboutUsInfo> {
                        InfoAboutUsScreen(
                            onClickBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable<ScreenChangePasscode> {
                        ChangePasscodeScreen(
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
object ScreenLogin

@Serializable
object ScreenRegister

@Serializable
data class ScreenHome(          //diqqet: bu data class oldu, yuxaridaki ise object!!!
    val name: String //argument
)

@Serializable
data class ScreenHistory(
    val isComingFromProfile: Boolean
)

@Serializable
object ScreenProfile

@Serializable
object ScreenMedicalInfo

@Serializable
object ScreenTermsInfo

@Serializable
object ScreenAboutUsInfo

@Serializable
object ScreenChangePasscode

@Serializable
object ScreenLock