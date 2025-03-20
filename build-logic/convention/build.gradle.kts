import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

//group = "io.devlight.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
        freeCompilerArgs.add("-Xcontext-receivers")
    }
}


gradlePlugin{
    plugins{
//        register("androidDetekt") {
//            id = "android.detekt"
//            implementationClass = "AndroidDetektConventionPlugin"
//        }
        register("androidKtlint") {
            id = "android.ktlint"
            implementationClass = "AndroidKtlintConventionPlugin"
        }

    }
}

dependencies {
  //  implementation(libs.android.gradle.plugin)
    implementation(libs.android.tools.common)
    implementation(libs.kotlin.gradle.plugin)
//    implementation(libs.ktlint.gradle)
   // implementation(libs.gradle.versions.plugin)
}
