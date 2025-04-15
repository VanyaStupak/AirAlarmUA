//package dev.devlight.skeleton.convention.source.conventionplugin.android
//
//import com.android.build.gradle.AppExtension
//import dev.devlight.skeleton.convention.core.ext.plugins
//import dev.devlight.skeleton.convention.core.ext.unaryPlus
//import dev.devlight.skeleton.convention.core.utils.getDebugReleaseNotes
//import dev.devlight.skeleton.convention.core.utils.gitBranch
//import com.google.firebase.appdistribution.gradle.AppDistributionExtension
//import org.gradle.api.Plugin
//import org.gradle.api.Project
//
///**
// * A custom plugin to configure Firebase App Distribution for an Android project.
// *
// * This plugin applies the Firebase App Distribution plugin and configures it for the
// * release and debug build types. The plugin sets the artifact type to "APK", assigns
// * tester groups, and specifies the service credentials file for authentication.
// *
// * References:
// *
// * - `/configure/README.md`
// */
//// TODO: add release notes
//class AppDistributionConventionPlugin : Plugin<Project> {
//
//    val internalTesterGroup: String = "TODO"
//    val externalTesterGroup: String = "TODO"
//
//    /**
//     * Applies the plugin to the given target project.
//     *
//     * @param target the target project
//     */
//    override fun apply(target: Project) = with(target) {
//        plugins {
//            +"com.google.firebase.appdistribution"
//        }
//        project.plugins.withId("com.android.application") {
//            val android = project.extensions.getByType(AppExtension::class.java)
//            android.apply {
//                buildTypes {
//                    findByName("release")?.apply {
//                        project.extensions.findByType(AppDistributionExtension::class.java)?.apply {
//                            artifactType = "APK"
//                            groups = externalTesterGroup
//                            serviceCredentialsFile = getServiceCredentialsFile(this@with)
//                            // release notes will contain all tickets which are new (not present in development)
//                            // TODO: update
//                            releaseNotes= getDebugReleaseNotes(gitBranch(), "main")
//                        }
//                    }
//                    findByName("debug")?.apply {
//                        project.extensions.findByType(AppDistributionExtension::class.java)?.apply {
//                            artifactType = "APK"
//                            groups = externalTesterGroup
//                            serviceCredentialsFile = getServiceCredentialsFile(this@with)
//                            // release notes will contain all tickets which are new (not present in development)
//                            releaseNotes= getDebugReleaseNotes(gitBranch(), "development")
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * Gets the path to the service credentials file.
//     *
//     * @param project the project
//     * @return the path to the service credentials file
//     */
//    private fun getServiceCredentialsFile(project: Project): String {
//        return "${project.rootProject.projectDir.absolutePath}/configure/credential/$FILE_NAME"
//    }
//
//    companion object {
//        /**
//         * Credential filename
//         */
//        const val FILE_NAME = "appdistribution-android.json"
//    }
//
//}