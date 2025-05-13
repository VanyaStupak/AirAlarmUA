plugins {
    id("dev.stupak.airalarmua.convention.feature")
    id("dev.stupak.airalarmua.convention.di")
    id("dev.stupak.airalarmua.convention.common.detekt")
    id("dev.stupak.airalarmua.convention.common.ktlint")
}

android {
    namespace = "com.example.widget"
}

dependencies {
    implementation(project(":domain:usecase"))
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":data:repository"))
    implementation(project(":presentation:core:platform"))
    implementation(project(":presentation:core:localisation"))

    implementation(libs.work.runtime.ktx)
    implementation (libs.gson)
    implementation (libs.androidx.glance.appwidget)
    implementation (libs.androidx.foundation)
    implementation (libs.androidx.navigation.compose)

}