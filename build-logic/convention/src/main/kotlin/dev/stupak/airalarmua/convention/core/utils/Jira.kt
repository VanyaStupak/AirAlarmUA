package dev.stupak.airalarmua.convention.core.utils

/**
 * Extracts a ticket number from the current git branch name.
 *
 * Assumes the git branch name is in the format `feature/<ticket>` or `<ticket>`.
 * Extracts the ticket number from the last part of the branch name.
 *
 * @return The extracted ticket number.
 */
fun getTicket(): String {
    val gitBranch = gitBranch()
    val ticket = gitBranch.split("/")
    return if (ticket.size == 2) ticket[1] else ticket[0]
}

/**
 * Extracts a Jira issue link from a commit message.
 *
 * This function searches for a Jira issue ID within the commit message using a regular expression
 * and constructs a URL to the issue if found.
 *
 * @param commitMessage the commit message to search for the Jira issue
 * @param ticket the Jira ticket prefix, default is "dev.devlight.skeleton-"
 * @param path the base URL for the Jira issue, default is "https://devlight.atlassian.net/browse/"
 * @return the URL to the Jira issue if found, otherwise null
 */
fun extractJiraIssueFromCommitMessage(
    commitMessage: String,
    ticket: String = "dev.devlight.skeleton-",
    path: String = "https://devlight.atlassian.net/browse/"
): String? {
    val jiraIssueRegex = Regex("\\($ticket(\\d+)\\)")
    val matchResult = jiraIssueRegex.find(commitMessage)
    return matchResult?.groups?.get(1)?.value?.let { "$path$ticket$it" }
}