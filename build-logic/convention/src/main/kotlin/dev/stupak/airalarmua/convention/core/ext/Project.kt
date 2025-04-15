package dev.stupak.airalarmua.convention.core.ext

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jmailen.gradle.kotlinter.KotlinterExtension

/**
 * Retrieves the [VersionCatalog] from the project's extensions.
 *
 * This property provides access to the `VersionCatalog` named "libs" from the project's
 * extensions. It simplifies the process of accessing library versions and dependencies
 * defined in the version catalog.
 *
 * Usage:
 *
 * ```kotlin
 * val versionCatalog = project.libs
 * val libraryVersion = versionCatalog.findLibrary("some.library").get()
 * ```
 *
 * @return The [VersionCatalog] instance associated with the name "libs".
 */
val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

/**
 * Retrieves version as a [String] from the project's extensions by the given name.
 *
 * This extension function simplifies the process of accessing specific version
 * from the version catalog.
 *
 * Usage:
 *
 * ```kotlin
 * val version: String = project.getVersionAsString("someVersion")
 * ```
 *
 * @param name The exact name of the version entry in the version catalog.
 *
 * @return The version as a [String].
 */
fun Project.getVersionAsString(name: String) = libs.findVersion(name).get().toString()

/**
 * Retrieves version as a [Int] from the project's extensions by the given name.
 *
 * This extension function simplifies the process of accessing specific version
 * from the version catalog.
 *
 * Usage:
 *
 * ```kotlin
 * val version: Int = project.getVersionAsInt("someVersion")
 * ```
 *
 * @param name The exact name of the version entry in the version catalog.
 *
 * @return The version as a [Int].
 */
fun Project.getVersionAsInt(name: String) = libs.findVersion(name).get().toString().toInt()

/**
 * Configures the [ApplicationExtension] for an Android project.
 *
 * This extension function allows you to configure the `ApplicationExtension` for an Android project
 * using a lambda expression. It provides a convenient way to set up application-specific configurations.
 *
 * Usage:
 *
 * ```kotlin
 * project.app {
 *     defaultConfig {
 *         applicationId = "com.example.app"
 *     }
 *     buildTypes {
 *         getByName("release") {
 *             isMinifyEnabled = true
 *         }
 *     }
 * }
 * ```
 *
 * @param block A lambda expression that configures the [ApplicationExtension] for the project.
 */
@Suppress("unused")
fun Project.app(block: ApplicationExtension.() -> Unit) {
    this.extensions.configure<ApplicationExtension> {
        this.block()
    }
}

/**
 * Configures the [LibraryExtension] for an Android library project.
 *
 * This extension function allows you to configure the `LibraryExtension` for an Android library project
 * using a lambda expression. It provides a convenient way to set up library-specific configurations.
 *
 * Usage:
 *
 * ```kotlin
 * project.lib {
 *     defaultConfig {
 *         minSdk = 21
 *         targetSdk = 30
 *     }
 *     buildTypes {
 *         getByName("release") {
 *             isMinifyEnabled = true
 *         }
 *     }
 * }
 * ```
 *
 * @param block A lambda expression that configures the [LibraryExtension] for the project.
 */
@Suppress("unused")
fun Project.lib(block: LibraryExtension.() -> Unit) {
    this.extensions.configure<LibraryExtension> {
        this.block()
    }
}

/**
 * Configures plugins for the project.
 *
 * This extension function provides a convenient way to configure plugins for a project
 * using a lambda expression. It allows you to add or configure plugins in a project context.
 *
 * Usage:
 *
 * ```kotlin
 * project.plugins {
 *     id("com.android.application")
 *     id("org.jetbrains.kotlin.android")
 * }
 * ```
 *
 * @param block A lambda expression that configures the [PluginManager] for the project.
 */
@Suppress("unused")
fun Project.plugins(block: PluginManager.() -> Unit) {
    this.pluginManager.block()
}

/**
 * Configures the [DetektExtension] for the project.
 *
 * This extension function allows you to configure Detekt settings for the project
 * using a lambda expression. It provides a convenient way to set up static code analysis
 * with Detekt.
 *
 * Usage:
 *
 * ```kotlin
 * project.detekt {
 *     buildUponDefaultConfig = true
 *     allRules = true
 *     config.setFrom(file("configure/detekt/detekt.yml"))
 * }
 * ```
 *
 * @param block A lambda expression that configures the [DetektExtension] for the project.
 */
@Suppress("unused")
fun Project.detekt(block: DetektExtension.() -> Unit) {
    val detektScope = extensions.getByType<DetektExtension>()
    detektScope.block()
}

/**
 * Configures the [KotlinterExtension] for the project.
 *
 * This extension function allows you to configure Ktlint settings for the project
 * using a lambda expression. It provides a convenient way to set up code style enforcement
 * with Ktlint.
 *
 * Usage:
 *
 * ```kotlin
 * project.ktlint {
 *     android.set(true)
 *     debug.set(true)
 *     experimental.set(true)
 * }
 * ```
 *
 * @param block A lambda expression that configures the [KotlinterExtension] for the project.
 */
@Suppress("unused")
fun Project.ktlint(block: KotlinterExtension.() -> Unit) {
    val ktlintScope = extensions.getByType<KotlinterExtension>()
    ktlintScope.block()
}


@Suppress("unused")
fun Project.jvm(block: JavaPluginExtension.() -> Unit) {
    this.extensions.configure<JavaPluginExtension> {
        this.block()
    }
}