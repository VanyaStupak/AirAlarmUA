plugins {
    id("dev.stupak.airalarmua.convention.feature")
    id("dev.stupak.airalarmua.convention.di")
    id("dev.stupak.airalarmua.convention.common.detekt")
    id("dev.stupak.airalarmua.convention.common.ktlint")
}

android {
    namespace = "com.example.settings"
}

dependencies {

    implementation(project(":domain:usecase"))
    implementation(project(":core:ui"))
    implementation(project(":presentation:core:platform"))
    implementation(project(":presentation:core:localisation"))

    implementation(libs.androidx.hilt.navigation.compose)
    implementation (libs.androidx.navigation.compose)
}