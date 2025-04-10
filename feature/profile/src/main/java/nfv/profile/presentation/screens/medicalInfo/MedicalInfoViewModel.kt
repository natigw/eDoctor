package nfv.profile.presentation.screens.medicalInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.ui_kit.model.BloodType
import nfv.ui_kit.model.Gender
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MedicalInfoViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel() {

    val uiState = MutableStateFlow(
        MedicalInfoState(
            userDetails = UserDetails(
                fullName = "Cart",
                bloodType = BloodType.FIRST_NEGATIVE,
                gender = Gender.MALE,
                weight = 65.0,
                birthDate = Date()
            ),
            allergies = emptyList(),
            diagnoses = emptyList(),
            medications = emptyList(),
            labTests = emptyList(),
            doctorContacts = emptyList()
        )
    )

    fun handleEvent(event: MedicalInfoEvent) {

        when(event) {

            MedicalInfoEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        popBackStack()
                    }
                }
            }

            MedicalInfoEvent.GoToAllergies -> {
                viewModelScope.launch {
                    navigator.sendCommand {
//                        navigate(route = )
                    }
                }
            }

            MedicalInfoEvent.OnEditDetailsClicked -> {
                viewModelScope.launch {
                    //action
                }
            }
        }
    }

}