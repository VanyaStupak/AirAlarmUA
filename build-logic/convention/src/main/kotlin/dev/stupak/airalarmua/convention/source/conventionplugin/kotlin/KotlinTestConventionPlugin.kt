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
            +"dev.stupak.airalarmua.convention.di"
        }
        with(libs) {
            dependencies {
                implementTestDependency("junit")
                implementTestDependency("mockk")
                implementTestDependency("kotlinx.coroutines.test")
            }
        }
    }
}
