package nfv.profile.presentation.screens.medicalInfo

import nfv.ui_kit.model.Allergy
import nfv.ui_kit.model.BloodType
import nfv.ui_kit.model.Gender
import java.util.Date

data class MedicalInfoState(
    val userDetails: UserDetails,
    val allergies: List<Allergy>,
    val diagnoses: List<String>,
    val medications: List<String>,
    val labTests: List<String>,
    val doctorContacts: List<String>
)

data class UserDetails(
    val fullName: String,
    val bloodType: BloodType,
    val gender: Gender,
    val weight: Double,
    val birthDate: Date,
)