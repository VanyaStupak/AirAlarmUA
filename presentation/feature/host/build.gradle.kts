plugins {
    id("dev.stupak.airalarmua.convention.feature")
    id("dev.stupak.airalarmua.convention.di")
    id("dev.stupak.airalarmua.convention.common.detekt")
    id("dev.stupak.airalarmua.convention.common.ktlint")
}

android {
    namespace = "dev.stupak.host"
}

dependencies {
    implementation(project(":presentation:feature:main"))
    implementation(project(":presentation:feature:welcome"))
    implementation(project(":presentation:feature:settings"))
    implementation(project(":presentation:feature:widget"))
    implementation(project(":presentation:core:navigation"))
    implementation(project(":presentation:core:worker"))
    implementation(project(":domain:usecase"))
    implementation(project(":core:ui"))

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.work)
    implementation(libs.work.runtime.ktx)
    implementation (libs.androidx.navigation.compose)


}