package dev.stupak.airalarmua.convention.source.conventionplugin.kotlin

import dev.stupak.airalarmua.convention.core.ext.jvm
import dev.stupak.airalarmua.convention.core.ext.plugins
import dev.stupak.airalarmua.convention.core.ext.unaryPlus
import dev.stupak.airalarmua.convention.source.config.ProjectConfig
import org.gradle.api.Plugin
import org.gradle.api.Project


@Suppress("unused")
class KotlinLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        plugins {
            +"org.jetbrains.kotlin.jvm"
        }
        jvm {
            sourceCompatibility = ProjectConfig.JAVA_VERSION
            targetCompatibility = ProjectConfig.JAVA_VERSION
        }
    }
}

