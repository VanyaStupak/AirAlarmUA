plugins {
    id("dev.stupak.airalarmua.convention.feature")
    id("dev.stupak.airalarmua.convention.di")
    id("dev.stupak.airalarmua.convention.common.detekt")
    id("dev.stupak.airalarmua.convention.common.ktlint")
    id("dev.stupak.airalarmua.convention.test.kotlin")
}

android {
    namespace = "dev.stupak.usecase.impl"
}

dependencies {
    implementation(project(":data:repository"))
    implementation(project(":domain:usecase"))
    implementation(libs.androidx.hilt.work)
}