package nfv.profile.presentation.screens.termsConditions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import javax.inject.Inject

@HiltViewModel
class TermsConditionsViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    fun handleEvent(event: TermsConditionsEvent) {

        when (event) {

            TermsConditionsEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        popBackStack()
                    }
                }
            }

        }
    }

}