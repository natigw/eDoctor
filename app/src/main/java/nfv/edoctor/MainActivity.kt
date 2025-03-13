package nfv.edoctor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import nfv.home.HomeScreen
import nfv.home.OnBoardScreen
import nfv.ui_kit.components.buttons.ButtonState
import nfv.ui_kit.theme.EDoctorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            EDoctorTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenOnBoard
                ) {
                    composable<ScreenOnBoard> {
                        OnBoardScreen(
                            onClickNext = (ButtonState.ENABLED) -> {
                                navController.navigate(ScreenHome(
                                    name = "Natigue"
                                ))
                    }
                        )
                    }
                    composable<ScreenHome> {
                        val args = it.toRoute<ScreenHome>()
                        HomeScreen(username = args.name)
                    }
                }
            }
        }
    }
}

@Serializable
object ScreenOnBoard

@Serializable
data class ScreenHome (          //diqqet: bu data class oldu, yuxaridaki ise object!!!
    val name: String //argument
)