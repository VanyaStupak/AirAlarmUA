plugins {
    id("dev.stupak.airalarmua.convention.feature")
    id("dev.stupak.airalarmua.convention.di")
    id("dev.stupak.airalarmua.convention.common.detekt")
    id("dev.stupak.airalarmua.convention.common.ktlint")
}

android {
    namespace = "dev.stupak.welcome"
}

dependencies {

    implementation(project(":domain:usecase"))
    implementation(project(":core:ui"))
    implementation(project(":presentation:core:platform"))

    implementation(libs.androidx.hilt.navigation.compose)
    implementation (libs.androidx.navigation.compose)

}