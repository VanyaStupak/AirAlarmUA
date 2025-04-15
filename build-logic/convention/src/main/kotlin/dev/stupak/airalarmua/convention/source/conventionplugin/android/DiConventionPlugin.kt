package dev.stupak.airalarmua.convention.source.conventionplugin.android


import dev.stupak.airalarmua.convention.core.ext.implementDependency
import dev.stupak.airalarmua.convention.core.ext.implementKapt
import dev.stupak.airalarmua.convention.core.ext.libs
import dev.stupak.airalarmua.convention.core.ext.plugins
import dev.stupak.airalarmua.convention.core.ext.unaryPlus
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DiConventionPlugin : Plugin<Project> {

        override fun apply(target: Project) = with(target) {
            plugins {
                +"com.google.dagger.hilt.android"
                +"org.jetbrains.kotlin.kapt"
            }
            pluginManager.apply("com.google.dagger.hilt.android")
            pluginManager.apply("org.jetbrains.kotlin.kapt")

            with(libs) {
                dependencies {
                    implementDependency("dagger.hilt.android")
                    implementKapt("com.google.dagger.hilt.compiler")
                    implementKapt("androidx.hilt.compiler")
                }
            }
        }

}