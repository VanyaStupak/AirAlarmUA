package dev.stupak.airalarmua.convention.core.ext

import com.android.build.api.dsl.Lint
import org.gradle.api.plugins.PluginManager
import java.util.Locale

/**
 * Applies a plugin to the project using its plugin ID.
 *
 * This operator function allows you to apply a plugin to the project by using the unary
 * plus (`+`) operator with the plugin ID as a string. It provides a concise syntax for
 * applying plugins.
 *
 * Usage:
 *
 * ```kotlin
 * plugins {
 *     +"com.android.application"
 *     +"org.jetbrains.kotlin.android"
 * }
 * ```
 *
 * @receiver The ID of the plugin to be applied.
 */
context(PluginManager)
operator fun String.unaryPlus() {
    apply(this)
}

/**
 * Adds a lint rule ID to the list of disabled lint checks.
 *
 * This operator function allows you to disable a lint rule by using the unary minus (`-`) operator
 * with the rule ID as a string. It provides a convenient syntax for managing lint checks in your project.
 *
 * Usage:
 *
 * ```kotlin
 * lintOptions {
 *     // Disable specific lint checks
 *     -"UnusedResources"
 *     -"MissingTranslation"
 * }
 * ```
 *
 * @receiver The ID of the lint rule to be disabled.
 */
context(Lint)
operator fun String.unaryMinus() {
    disable.add(this)
}

/**
 * Capitalizes the first character of a string, converting it to title case.
 * If the first character is already uppercase, the string is returned unchanged.
 *
 * @return A new string with the first character capitalized.
 */
fun String.capitalize() =
    replaceFirstChar {
        if (it.isLowerCase()) {
            it.titlecase(Locale.US)
        } else {
            it.toString()
        }
    }

/**
 * Checks if the string ends with the specified file extension.
 *
 * @param extension The file extension to check, without the leading dot (e.g., "apk" not ".apk").
 * @return `true` if the string ends with the specified extension, `false` otherwise.
 * @throws IllegalArgumentException if the provided extension contains a dot.
 */
fun String.containsExtension(extension: String): Boolean {
    require(!extension.contains(".")) {
        "Please, use file extension without \".\" (e.g., \"apk\" not \".apk\")"
    }
    return this.endsWith(extension)
}
