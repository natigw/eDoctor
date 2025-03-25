package nfv.edoctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import nfv.auth.navigation.authNavigation
import nfv.navigation.LocalNavController
import nfv.navigation.di.Navigator
import nfv.navigation.routes.AuthNavigation
import nfv.ui_kit.theme.EDoctorTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
//        WindowCompat.setDecorFitsSystemWindows(window, false)  //TODO -> bu neye lazimdi
        setContent {

            val navController = rememberNavController()

            LaunchedEffect(Unit) {
                navigator.observe(this) { onCommand ->
                    onCommand(navController)
                }
            }

            CompositionLocalProvider(
                LocalNavController provides navController
            ) {
                EDoctorTheme {

                    val focusManager = LocalFocusManager.current
                    NavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.primary)
                            .clickable(indication = null, interactionSource = null) {
                                focusManager.clearFocus()
                            },
                        navController = navController,
                        startDestination = AuthNavigation
                    ) {
                        authNavigation()
                    }
                }
            }
        }
    }
}
//
//val navController = rememberNavController()
////                val focusManager = LocalFocusManager.current   //TODO -> bu neye lazimdi
//
//NavHost(
////                    modifier = Modifier  //TODO -> bu neye lazimdi
////                        .fillMaxSize()
////                        .background(color = Primary500)
////                        .clickable(indication = null, interactionSource = null) {
////                            focusManager.clearFocus()
////                        },
//navController = navController,
//startDestination = ScreenOnBoard
//) {
//    composable<ScreenOnBoard> {
//        OnBoardScreen(
//            onClickNext = {
//                navController.navigate(ScreenLogin)
//            }
//        )
//    }
//    composable<ScreenLogin> {
//        LoginScreen(
//            onClickGoogleLogin = {
//                navController.navigate(ScreenHistory(isComingFromProfile = false))
//            },
//            onClickRegister = {
//                navController.navigate(ScreenRegisterForm)
//            }
//        )
//    }
//    composable<ScreenRegister> {
//        RegisterScreen()
//    }
//    composable<ScreenRegisterForm> {
//        RegisterFormScreen(
//            state = RegisterFormState(
//                fullNameText = "",
//                emailText = "",
//                passwordText = "",
//                confirmPasswordText = "",
//                passwordStrength = PasswordStrength.NONE,
//                continueButtonState = ButtonState.DISABLED
//            ),
//            onUiEvent = { }
//        )
//    }
//
//    composable<ScreenHome> {
//        val args = it.toRoute<ScreenHome>()
//        HomeScreen(
//            state = HomeState(username = args.name),
//            onUiEvent = {
//
//            },
////                            onGoToHome = {
////
////                            },
////                            onGotoHistory = {
////                                navController.navigate(ScreenHistory(isComingFromProfile = false))
////                            },
////                            onGoToProfile = {
////                                navController.navigate(ScreenProfile)
////                            }
//        )
//    }
//
//    composable<ScreenHistory> {
//        val args = it.toRoute<ScreenHistory>()
//
//        val context = LocalContext.current
//        val downloader = remember { AndroidDownloader(context) }
//
//        TestResultsScreen(
//            isComingFromProfile = args.isComingFromProfile,
//            onGoToHome = {
//                navController.navigate(ScreenHome(name = "Natihistory"))
//            },
//            onGotoHistory = {
//
//            },
//            onGoToProfile = {
//                navController.navigate(ScreenProfile)
//            },
//            onClickBack = {
//                navController.popBackStack()
//            },
//            onClickDownloadDocument = { title ->
//                downloader.downloadFile(
//                    url = "https://pl-coding.com/wp-content/uploads/2022/04/pic-squared.jpg",
//                    titleAppendix = title
//                )
//            }
//        )
//    }
//
//    composable<ScreenProfile> {
//        ProfileScreen(
//            onGoToHome = {
//                navController.navigate(ScreenHome(name = "Natigg"))
//            },
//            onGotoHistory = {
//                navController.navigate(ScreenHistory(isComingFromProfile = false))
//            },
//            onGoToProfile = {
//
//            },
//            onClickMedicalInfo = {
//                navController.navigate(ScreenMedicalInfo)
//            },
//            onGoToChangePasscode = {
//                navController.navigate(ScreenChangePasscode)
//            },
//            onGoToTermsInfo = {
//                navController.navigate(ScreenTermsInfo)
//            },
//            onGoToAboutUsInfo = {
//                navController.navigate(ScreenAboutUsInfo)
//            }
//        )
//    }
//
//    composable<ScreenMedicalInfo> {
//        MedicalInfoScreen(
//            onClickBack = {
//                navController.popBackStack()
//            },
//            onClickLabTests = {
//                navController.navigate(ScreenHistory(isComingFromProfile = true))
//            }
//        )
//    }
//
//    composable<ScreenTermsInfo> {
//        InfoTermsConditionsScreen(
//            onClickBack = {
//                navController.popBackStack()
//            }
//        )
//    }
//
//    composable<ScreenAboutUsInfo> {
//        InfoAboutUsScreen(
//            onClickBack = {
//                navController.popBackStack()
//            }
//        )
//    }
//
//    composable<ScreenChangePasscode> {
//        ChangePasscodeScreen(
//            onClickBack = {
//                navController.popBackStack()
//            }
//        )
//    }
//
//    composable<ScreenLock> {
//        LockScreen()
//    }
//}
//}
//}
//}
//}
//}
//
//@Serializable
//object ScreenOnBoard
//
//@Serializable
//object ScreenLogin
//
//@Serializable
//object ScreenRegister
//
//@Serializable
//object ScreenRegisterForm
//
//@Serializable
//data class ScreenHome(          //diqqet: bu data class oldu, yuxaridaki ise object!!!
//    val name: String //argument
//)
//
//@Serializable
//data class ScreenHistory(
//    val isComingFromProfile: Boolean
//)
//
//@Serializable
//object ScreenProfile
//
//@Serializable
//object ScreenMedicalInfo
//
//@Serializable
//object ScreenTermsInfo
//
//@Serializable
//object ScreenAboutUsInfo
//
//@Serializable
//object ScreenChangePasscode
//
//@Serializable
//object ScreenLock