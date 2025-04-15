package dev.stupak.airalarmua.convention.core.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Generates debug release notes by comparing commit logs between two branches.
 *
 * This function retrieves commit logs between the current and target branches, extracts Jira issue
 * links from the commit messages, and formats them into a changelog.
 *
 * @param currentBranch the name of the current branch
 * @param targetBranch the name of the target branch to compare against
 * @return the formatted debug release notes as a string
 */
fun getDebugReleaseNotes(
    currentBranch: String, targetBranch: String
): String {
    val datetime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now())
    val tickets = getCommitLogsBetweenBranches(currentBranch, targetBranch).map {
        extractJiraIssueFromCommitMessage(it)
    }.toSet().filterNotNull().joinToString( "\n").ifBlank { "no changes ⛔" }
        return """📋 Changelog:
        |
        |📅 $datetime
        |
        |🔖 Involved tickets:
        |
        |$tickets
    """.trimMargin()

}
