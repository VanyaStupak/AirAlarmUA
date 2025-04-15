package dev.stupak.airalarmua.convention.core.utils

import java.io.File
import java.nio.file.InvalidPathException
import java.nio.file.Paths

/**
 * Checks if the string is a valid email address.
 *
 * @return `true` if the string matches the email regex, `false` otherwise.
 */
val String.isEmail: Boolean
    get() = this.matches(Regex(RegexUtils.EMAIL_REGEX))

/**
 * Checks if the string is a valid Jira Cloud instance URL.
 *
 * @return `true` if the string matches the Jira Cloud instance regex, `false` otherwise.
 */
val String.isJiraCloudInstance: Boolean
    get() = this.matches(Regex(RegexUtils.JIRA_CLOUD_INSTANCE_REGEX))

/**
 * Checks if the string is a valid Jira ticket identifier.
 *
 * @return `true` if the string matches the Jira ticket regex, `false` otherwise.
 */
val String.isJiraTicket: Boolean
    get() = this.matches(Regex(RegexUtils.JIRA_TICKET_REGEX))

/**
 * Checks if the string is a valid file path.
 *
 * This property attempts to create a `Path` from the string and returns `false` if an
 * [InvalidPathException] or [NullPointerException] is thrown.
 *
 * @return `true` if the string is a valid file path, `false` otherwise.
 */
@Suppress("TooGenericExceptionCaught", "SwallowedException", "MemberVisibilityCanBePrivate")
val String.isPathValid: Boolean
    get() {
        return try {
            Paths.get(this)
            true
        } catch (ex: InvalidPathException) {
            false
        } catch (ex: NullPointerException) {
            false
        }
    }

/**
 * Checks if the string is a valid file path and if the file exists and is not empty.
 *
 * This property first checks if the path is valid using [isPathValid], then verifies
 * if the file exists and has a non-zero length.
 *
 * @return `true` if the string is a valid path to an existing non-empty file, `false` otherwise.
 */
val String.isFileValid: Boolean
    get() {
        if (!this.isPathValid) return false
        val tempFile = File(this)
        return tempFile.exists() && tempFile.length() > 0
    }

/**
 * Utility object that contains regular expressions for common validations.
 */
object RegexUtils {

    /**
     * Regular expression for validating email addresses.
     */
    const val EMAIL_REGEX = "^([a-zA-Z0-9._%+-]+)@([a-zA-Z0-9.-]+)\\.([a-zA-Z]{2,})$"

    /**
     * Regular expression for validating Jira Cloud instance URLs.
     */
    const val JIRA_CLOUD_INSTANCE_REGEX = "^https://[a-zA-Z0-9-]+\\.atlassian\\.net$"

    /**
     * Regular expression for validating Jira ticket identifiers.
     */
    const val JIRA_TICKET_REGEX = "^[A-Za-z][A-Za-z0-9]*-[0-9]+\$"
}
