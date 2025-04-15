package dev.stupak.airalarmua.convention.source.conventionplugin.kotlin

import dev.stupak.airalarmua.convention.core.ext.implementTestDependency
import dev.stupak.airalarmua.convention.core.ext.implementTestRuntimeOnly
import dev.stupak.airalarmua.convention.core.ext.libs
import dev.stupak.airalarmua.convention.core.ext.plugins
import dev.stupak.airalarmua.convention.core.ext.unaryPlus
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class KotlinTestConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        plugins {
            +"dev.devlight.skeleton.convention.di"
        }
        tasks.withType<Test> {
            useJUnitPlatform()
        }
        with(libs) {
            dependencies {
                implementTestDependency("test.junit.api")
                implementTestRuntimeOnly("test.junit.jupiter")
                implementTestRuntimeOnly("test.junit.engine")
                implementTestDependency("test.junit.params")
                implementTestDependency("mockito.core")
                implementTestDependency("mockito.kotlin")
                implementTestDependency("kotlinx.coroutines.test")
                implementTestRuntimeOnly("test.junit.vintage")
                implementTestDependency("kotlin.test.junit5")
                implementTestDependency("mockk")
                implementTestDependency("turbine")
                implementTestDependency("truth")
            }
        }
    }
}
