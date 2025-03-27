package nfv.auth.presentation.screens.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.navigation.routes.OnBoardRoute
import nfv.navigation.routes.RegisterFormRoute
import nfv.navigation.routes.RegisterRoute
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

//    val uiState = MutableStateFlow(
//        OnBoardState(
//
//        )
//    )

    fun handleEvent(event: OnBoardEvent) {
        when(event) {

            OnBoardEvent.OnNextClicked -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(route = RegisterFormRoute)  //TODO -> bunu duzgun sehifeye yonlendir
                    }
                }
            }

            OnBoardEvent.OnSkipClicked -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(route = RegisterRoute)
                    }
                }
            }
        }
    }

}