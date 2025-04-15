plugins {
    id("dev.stupak.airalarmua.convention.application")
    id("dev.stupak.airalarmua.convention.di")
    id("dev.stupak.airalarmua.convention.common.detekt")
    id("dev.stupak.airalarmua.convention.common.ktlint")
}

android {
    namespace = "dev.stupak.airalarmua"
}

dependencies {

    implementation(project(":core:ui"))
    implementation(project(":presentation:feature:main"))
    implementation(project(":presentation:feature:welcome"))
    implementation(project(":presentation:feature:settings"))
    implementation(project(":presentation:feature:widget"))
    implementation(project(":presentation:feature:host"))
    implementation(project(":presentation:core:worker"))
    implementation(project(":data:local:impl"))
    implementation(project(":data:network:impl"))
    implementation(project(":data:repository:impl"))
    implementation(project(":data:database:impl"))
    implementation(project(":domain:usecase:impl"))

    implementation(libs.androidx.hilt.work)
    implementation(libs.work.runtime.ktx)
}