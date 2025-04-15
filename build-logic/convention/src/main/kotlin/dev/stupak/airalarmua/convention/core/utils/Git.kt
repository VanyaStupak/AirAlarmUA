package dev.stupak.airalarmua.convention.core.utils

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Returns the current git branch name.
 *
 * This function executes the `git rev-parse --abbrev-ref HEAD` command to determine the current git branch.
 *
 * @return The name of the current git branch, or an empty string if the command fails.
 */
fun gitBranch(): String {
    val processBuilder = ProcessBuilder("git", "rev-parse", "--abbrev-ref", "HEAD")
    val process = processBuilder.start()
    val branch = process.inputStream.bufferedReader().use(BufferedReader::readText).trim()
    process.errorStream.bufferedReader().useLines { lines ->
        lines.forEach { println(it) }
    }
    process.waitFor()
    return branch
}

/**
 * Returns a debug suffix based on the current git branch.
 *
 * This function retrieves the current git branch using `gitBranch()` and appends it to a hyphenated prefix.
 *
 * @return A debug suffix in the format "-<branch_name>".
 */
fun getDebugSuffix(): String {
    val branch = getTicket()
    return "-$branch"
}

/**
 * Retrieves the commit logs between two branches in a Git repository.
 *
 * This function runs a Git command to get the commit logs from the target branch to the current branch
 * in a one-line format.
 *
 * @param current the name of the current branch
 * @param target the name of the target branch
 * @return a list of commit logs as strings
 */
fun getCommitLogsBetweenBranches(current: String, target: String): MutableList<String> {
    val processBuilder = ProcessBuilder("git", "log", "--pretty=oneline", "$target..$current")
    val process = processBuilder.start()

    val reader = BufferedReader(InputStreamReader(process.inputStream))
    val lines = mutableListOf<String>()
    var line: String?
    while (reader.readLine().also { line = it } != null) {
        lines.add(line!!)
    }
    reader.close()

    process.waitFor()
    return lines
}