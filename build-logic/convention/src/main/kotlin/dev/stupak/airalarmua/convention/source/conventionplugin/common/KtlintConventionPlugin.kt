package dev.stupak.airalarmua.convention.source.conventionplugin.common

import dev.stupak.airalarmua.convention.core.ext.ktlint
import dev.stupak.airalarmua.convention.core.ext.plugins
import dev.stupak.airalarmua.convention.core.ext.unaryPlus
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jmailen.gradle.kotlinter.tasks.LintTask

@Suppress("unused")
class KtlintConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            plugins {
                +"org.jmailen.kotlinter"
            }

            ktlint {
                ignoreLintFailures = false
                reporters = arrayOf("plain", "html")
            }
            configureTasks()
        }
    }

    private fun Project.configureTasks() {
        tasks.withType<LintTask>().configureEach {
            reports.set(
                mapOf(
//                    "plain" to rootDir.resolve("build/reports/ktlint-${project.name}.txt"),
//                    "html" to rootDir.resolve("build/reports/ktlint-${project.name}.html")
                )
            )
            include("**/*.kt", "**/*.kts")
            exclude("**/build/**", "**/compose/**")
        }
    }

}