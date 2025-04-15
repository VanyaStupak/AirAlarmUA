package dev.stupak.airalarmua.convention.source.conventionplugin.common


import dev.stupak.airalarmua.convention.core.ext.detekt
import dev.stupak.airalarmua.convention.core.ext.plugins
import dev.stupak.airalarmua.convention.core.ext.unaryPlus
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class DetektConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        plugins {
            +"io.gitlab.arturbosch.detekt"
        }
        detekt {
            buildUponDefaultConfig = true
            ignoreFailures = true
            allRules = true
            config.setFrom("${rootProject.rootDir}$DETEKT_CONFIG_PATH")
        }
    }

    companion object {

        const val DETEKT_CONFIG_PATH = "/configure/detekt/detekt.yml"
    }
}