plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    // Serialization
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.hiltPlugin)
    kotlin("kapt")
}

android {
    namespace = "nfv.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":storage"))

    // Ktor
    api(libs.ktor.client.android)
    api(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    api(libs.ktor.client.serialization)
    implementation(libs.logging.interceptor)

    // Serialization
    api(libs.kotlinx.serialization.json)
    api(libs.gson)

    // Hilt
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}