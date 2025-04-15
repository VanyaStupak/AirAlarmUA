plugins {
    id("dev.stupak.airalarmua.convention.feature")
    id("dev.stupak.airalarmua.convention.di")
    id("dev.stupak.airalarmua.convention.common.detekt")
    id("dev.stupak.airalarmua.convention.common.ktlint")
}

android {
    namespace = "dev.stupak.worker"
}

dependencies {
    implementation(project(":domain:usecase"))
    implementation(project(":data:network"))
    implementation (libs.gson)
    implementation(libs.work.runtime.ktx)
    implementation(libs.androidx.hilt.work)
}