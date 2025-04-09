package nfv.ui_kit.model

enum class BloodType(val notation: String) {
    FIRST_NEGATIVE("O-"),
    FIRST_POSITIVE("O+"),
    SECOND_NEGATIVE("B-"),
    SECOND_POSITIVE("B+"),
    THIRD_NEGATIVE("A-"),
    THIRD_POSITIVE("A+"),
    FORTH_NEGATIVE("AB-"),
    FORTH_POSITIVE("AB+"),
    UNKNOWN("Unknown")
}