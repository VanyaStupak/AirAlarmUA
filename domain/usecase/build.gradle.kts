plugins {
    id("dev.stupak.airalarmua.convention.feature")
    id("dev.stupak.airalarmua.convention.common.detekt")
    id("dev.stupak.airalarmua.convention.common.ktlint")
}

android {
    namespace = "dev.stupak.usecase"
}

dependencies {
    implementation(project(":data:repository"))
    implementation(project(":core:exception"))
}