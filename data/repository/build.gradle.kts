plugins {
    id("dev.stupak.airalarmua.convention.feature")
    id("dev.stupak.airalarmua.convention.common.detekt")
    id("dev.stupak.airalarmua.convention.common.ktlint")
}

android {
    namespace = "dev.stupak.repository"
}

dependencies {
    implementation(project(":data:network"))
    implementation(project(":data:local"))
    implementation(project(":data:database"))
    implementation(libs.androidx.datastore)
}