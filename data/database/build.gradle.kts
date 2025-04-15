plugins {
    id("dev.stupak.airalarmua.convention.feature")
    id("dev.stupak.airalarmua.convention.di")
    id("dev.stupak.airalarmua.convention.common.detekt")
    id("dev.stupak.airalarmua.convention.common.ktlint")
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.stupak.database"
}

dependencies {
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
}