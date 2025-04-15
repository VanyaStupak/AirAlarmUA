import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

buildscript {
    dependencies {
        classpath(libs.android.gradle.plugin)
    }
}

group = "dev.stupak.airalarmua.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
//    compileOnly(libs.ksp.gradle.plugin)
    implementation(libs.detekt.gradle.plugin)
//    implementation(libs.secrets.gradle)
    implementation(libs.kotlinter.gradle)
//    implementation(libs.lint.checks)
//    implementation(libs.lint.api)
//    implementation(libs.compiler.report)
//    implementation(libs.bundles.http4k)
//    implementation(libs.httpclient5)
    compileOnly(libs.android.gradle.plugin)
    implementation(libs.gson)
   // implementation(libs.org.sonarqube.gradle.plugin)
}

gradlePlugin {
    plugins {

        //region android
        register("androidApplication") {
            id = "dev.stupak.airalarmua.convention.application"
            implementationClass =
                "dev.stupak.airalarmua.convention.source.conventionplugin.android.AndroidApplicationConventionPlugin"
        }
        register("androidFeature") {
            id = "dev.stupak.airalarmua.convention.feature"
            implementationClass =
                "dev.stupak.airalarmua.convention.source.conventionplugin.android.AndroidFeatureConventionPlugin"
        }
        register("androidTest") {
            id = "dev.stupak.airalarmua.convention.test.android"
            implementationClass =
                "dev.stupak.airalarmua.convention.source.conventionplugin.android.AndroidTestConventionPlugin"
        }
        register("androidDi") {
            id = "dev.stupak.airalarmua.convention.di"
            implementationClass =
                "dev.stupak.airalarmua.convention.source.conventionplugin.android.DiConventionPlugin"
        }
        register("AppDistributionConventionPlugin") {
            id = "dev.stupak.airalarmua.convention.appdistribution"
            implementationClass =
                "dev.stupak.airalarmua.convention.source.conventionplugin.android.AppDistributionConventionPlugin"
        }
        //endregion android

        //region kotlin
        register("kotlinLibrary") {
            id = "dev.stupak.airalarmua.convention.library"
            implementationClass =
                "dev.stupak.airalarmua.convention.source.conventionplugin.kotlin.KotlinLibraryConventionPlugin"
        }
        register("kotlinTest") {
            id = "dev.stupak.airalarmua.convention.test.kotlin"
            implementationClass =
                "dev.stupak.airalarmua.convention.source.conventionplugin.kotlin.KotlinTestConventionPlugin"
        }
        //endregion kotlin

        //region common
        register("commonDetekt") {
            id = "dev.stupak.airalarmua.convention.common.detekt"
            implementationClass =
                "dev.stupak.airalarmua.convention.source.conventionplugin.common.DetektConventionPlugin"
        }
        register("commonKtlint") {
            id = "dev.stupak.airalarmua.convention.common.ktlint"
            implementationClass =
                "dev.stupak.airalarmua.convention.source.conventionplugin.common.KtlintConventionPlugin"
        }
        register("miscellaneousPublish") {
            id = "dev.stupak.airalarmua.convention.miscellaneous.publish"
            implementationClass =
                "dev.stupak.airalarmua.convention.source.conventionplugin.miscellaneous.publish.PublishPlugin"
        }
        register("gradleSecrets") {
            id = "dev.stupak.airalarmua.convention.common.gradlesecrets"
            implementationClass =
                "dev.stupak.airalarmua.convention.source.conventionplugin.gradle.SecretsGradleConventionPlugin"
        }
        //endregion common
    }
}

// Necessary for context receiver
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
    }
}