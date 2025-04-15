package dev.stupak.airalarmua.convention.source.conventionplugin.android

import dev.stupak.airalarmua.convention.core.ext.implementAndroidTestDependency
import dev.stupak.airalarmua.convention.core.ext.implementDebugImplementation
import dev.stupak.airalarmua.convention.core.ext.libs
import dev.stupak.airalarmua.convention.core.ext.plugins
import dev.stupak.airalarmua.convention.core.ext.unaryPlus
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

@Suppress("unused", "KDocUnresolvedReference")
class AndroidTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        plugins {
            +"dev.devlight.skeleton.convention.di"
            +"dev.devlight.skeleton.convention.test.kotlin"
        }

        this.extensions.configure<com.android.build.gradle.LibraryExtension> {
            testOptions {
                unitTests.isIncludeAndroidResources = true
            }

            sourceSets {
                getByName("test").apply {
                    java.srcDirs("test")
                    resources.srcDirs("test/resources")
                }
            }

            defaultConfig {
                testInstrumentationRunner = "de.mannodermaus.junit5.AndroidJUnit5Builder"
                testInstrumentationRunnerArguments["runnerBuilder"] = "de.mannodermaus.junit5.AndroidJUnit5Builder"
            }
        }

        tasks.withType<Test> {
            useJUnitPlatform()
        }

        with(libs) {
            dependencies {
                // Android-specific testing dependencies
                implementAndroidTestDependency("mockk.android")
                implementAndroidTestDependency("androidx.compose.ui.test.junit4")
                implementDebugImplementation("androidx.compose.ui.test.manifest")
            }
        }
    }
}
