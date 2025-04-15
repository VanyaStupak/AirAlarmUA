plugins {
    id("dev.stupak.airalarmua.convention.feature")
    id("dev.stupak.airalarmua.convention.common.detekt")
    id("dev.stupak.airalarmua.convention.common.ktlint")
}

android {
    namespace = "dev.stupak.navigation"
}

dependencies {
    implementation(project(":presentation:feature:main"))
    implementation(project(":presentation:feature:welcome"))
    implementation(project(":presentation:feature:settings"))
    implementation (libs.androidx.navigation.compose)
}