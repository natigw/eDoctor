package nfv.profile.presentation.screens.aboutUs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import javax.inject.Inject

@HiltViewModel
class AboutUsViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    fun handleEvent(event: AboutUsEvent) {

        when (event) {

            AboutUsEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        popBackStack()
                    }
                }
            }

        }
    }

}