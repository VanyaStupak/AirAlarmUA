package dev.stupak.airalarmua.convention.source.conventionplugin.android

import dev.stupak.airalarmua.convention.core.ext.getVersionAsInt
import dev.stupak.airalarmua.convention.core.ext.getVersionAsString
import dev.stupak.airalarmua.convention.core.ext.implementDependency
import dev.stupak.airalarmua.convention.core.ext.implementKsp
import dev.stupak.airalarmua.convention.core.ext.lib
import dev.stupak.airalarmua.convention.core.ext.libs
import dev.stupak.airalarmua.convention.core.ext.plugins
import dev.stupak.airalarmua.convention.core.ext.unaryPlus
import dev.stupak.airalarmua.convention.source.config.ProjectConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File
import java.io.FileInputStream
import java.util.Properties


class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        plugins {
            +"com.android.library"
            +"org.jetbrains.kotlin.android"
            +"org.jetbrains.kotlin.plugin.serialization"
            +"kotlin-parcelize"
            +"org.jetbrains.kotlin.plugin.compose"
            +"com.google.devtools.ksp"
        }

        lib {
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = getVersionAsString("androidxComposeCompiler")
            }
            defaultConfig {
                compileSdk = getVersionAsInt("compileSdk")
                minSdk = getVersionAsInt("minSdk")
                @Suppress("DEPRECATION")
                targetSdk = getVersionAsInt("targetSdk")

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = ProjectConfig.JAVA_VERSION
                targetCompatibility = ProjectConfig.JAVA_VERSION
            }
//            signingConfigs {
//                getByName("debug") {
//                    val properties = Properties().also {
//                        it.load(
//                            FileInputStream(
//                                File(
//                                    project.rootDir,
//                                    "/configure/secrets/secrets.properties"
//                                )
//                            )
//                        )
//                    }
//                    keyAlias = properties.getProperty("debugKey")
//                    keyPassword = properties.getProperty("debugPassword")
//
//                    storePassword = properties.getProperty("debugPassword")
//                    storeFile = File(project.rootDir, "/configure/signing/aos-native-skeleton.debug")
//                }
//            }
            lint {
                xmlReport = true
                xmlOutput = file("build/reports/lint-results.xml")
            }
        }
        this.extensions.configure<com.android.build.gradle.LibraryExtension> {
            buildFeatures.buildConfig = true
        }
        with(libs) {
            dependencies {
                implementDependency("kotlinx.serialization.json")
                implementDependency("androidx.core.ktx")
                implementDependency("androidx.material3")
                implementDependency("androidx.ui.tooling")
                implementDependency("androidx.activity.compose")
                implementDependency("lifecycle.viewmodel.compose")
                implementDependency("compose.runtime")
                implementDependency("lifecycle.runtime.compose")
            }
        }
        // Necessary for context receiver
        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
            }
        }
    }

}

