package nfv.profile.presentation.screens.medicalInfo

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
    val sex: Sex,
    val weight: Double,
    val birthDate: Date,
)

enum class BloodType(val notation: String) {  //TODO -> register ekrani ile tekrar oldu, nece reuse etmeli??
    FIRST_NEGATIVE("O-"),
    FIRST_POSITIVE("O+"),
    SECOND_NEGATIVE("B-"),
    SECOND_POSITIVE("B+"),
    THIRD_NEGATIVE("A-"),
    THIRD_POSITIVE("A+"),
    FORTH_NEGATIVE("AB-"),
    FORTH_POSITIVE("AB+"),
}

enum class Sex(val displayName: String) {
    MALE("Male"),
    FEMALE("Female")
}

data class Allergy(
    val name: String,
    val severity: Severity
)

enum class Severity {
    LOW,
    MEDIUM,
    HIGH,
    FATAL
}