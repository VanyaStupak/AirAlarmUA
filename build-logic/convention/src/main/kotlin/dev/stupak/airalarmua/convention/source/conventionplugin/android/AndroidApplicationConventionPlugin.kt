package dev.stupak.airalarmua.convention.source.conventionplugin.android

import dev.stupak.airalarmua.convention.core.ext.app
import dev.stupak.airalarmua.convention.core.ext.getVersionAsInt
import dev.stupak.airalarmua.convention.core.ext.getVersionAsString
import dev.stupak.airalarmua.convention.core.ext.implementDependency
import dev.stupak.airalarmua.convention.core.ext.libs
import dev.stupak.airalarmua.convention.core.ext.plugins
import dev.stupak.airalarmua.convention.core.ext.unaryPlus
import dev.stupak.airalarmua.convention.source.config.ProjectConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Delete
import org.gradle.internal.os.OperatingSystem
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.register
import java.io.File
import java.io.FileInputStream
import java.util.Properties

class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        plugins {
            +"com.android.application"
            +"org.jetbrains.kotlin.android"
            +"org.jetbrains.kotlin.plugin.compose"
        }
        tasks.apply {
            register<Delete>("deleteGitHook") {
                val preCommit = "${rootProject.rootDir}/.git/hooks/pre-commit"
                if (file(preCommit).exists()) {
                    delete(preCommit)
                }
            }

            register<Copy>("installGitHook") {
                dependsOn("deleteGitHook")
                from("${rootProject.rootDir}/configure/githook/precommit/pre-commit")
                into("${rootProject.rootDir}/.git/hooks")
                fileMode = 777

                doFirst {
                    if (!OperatingSystem.current().isWindows) {
                        exec {
                            commandLine("chmod", "-R", "u+w", "${rootProject.rootDir}/.git/hooks")
                        }
                    }
                }
            }

            afterEvaluate {
                tasks.getByPath(":app:preBuild")
                    .dependsOn("installGitHook")
            }
        }

        app {
            buildFeatures {
                buildConfig = true
                compose = true
            }
            composeOptions {
               kotlinCompilerExtensionVersion = getVersionAsString("androidxComposeCompiler")
            }
            defaultConfig {
                applicationId = ProjectConfig.APPLICATION_ID
                compileSdk = getVersionAsInt("compileSdk")
                minSdk = getVersionAsInt("minSdk")
                targetSdk = getVersionAsInt("targetSdk")

                versionCode = getVersionAsInt("versionCode")
                versionName = getVersionAsString("versionName")
                vectorDrawables {
                    useSupportLibrary = true
                }
            }
            compileOptions {
                sourceCompatibility = ProjectConfig.JAVA_VERSION
                targetCompatibility = ProjectConfig.JAVA_VERSION
            }

            signingConfigs {
                getByName("debug") {
                    val properties = Properties().also {
                        it.load(
                            FileInputStream(
                                File(
                                    project.rootDir,
                                    "./configure/secrets/secrets.properties"
                                )
                            )
                        )
                    }
                    keyAlias = properties.getProperty("debugKey")
                    keyPassword = properties.getProperty("debugPassword")

                    storePassword = properties.getProperty("debugPassword")
                    storeFile =
                        File(project.rootDir, "./configure/signing/air-alarm-ua.debug")
                }
                create("release") {
                    val properties = Properties().also {
                        it.load(
                            FileInputStream(
                                File(
                                    project.rootDir,
                                    "./configure/secrets/secrets.properties"
                                )
                            )
                        )
                    }
                    keyAlias = properties.getProperty("releaseKey")
                    keyPassword = properties.getProperty("releasePassword")

                    storePassword = properties.getProperty("releasePassword")
                    storeFile =
                        File(project.rootDir, "./configure/signing/air-alarm-ua.jks")
                }
            }

            packaging {
                resources {
                    excludes += listOf(
                        "META-INF/LICENSE.md",
                        "META-INF/LICENSE-notice.md",
                    )
                }
            }
        }

        // Load the properties from the file
        val localPropertiesFile = File(project.rootDir, "local.properties")

        val localProperties = Properties().apply {
            if (localPropertiesFile.exists()) {
                localPropertiesFile.inputStream().use { fis ->
                    load(fis)
                }
            }
        }


        with(libs)
        {
            dependencies {
                implementDependency("androidx.core.ktx")
                implementDependency("androidx.material3")
                implementDependency("androidx.ui.tooling")
                implementDependency("androidx.ui.tooling.preview")
                implementDependency("androidx.activity.compose")
                implementDependency("lifecycle.viewmodel.compose")
                implementDependency("compose.runtime")
            }
        }

    }

}