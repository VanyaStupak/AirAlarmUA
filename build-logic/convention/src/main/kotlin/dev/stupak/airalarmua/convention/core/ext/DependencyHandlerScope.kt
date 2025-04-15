package dev.stupak.airalarmua.convention.core.ext

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.DependencyHandlerScope

context(VersionCatalog)
fun DependencyHandlerScope.implementDependency(value: String) {
    add("implementation", this@VersionCatalog.findLibrary(value).get())
}

context(Project)
@Suppress("unused")
fun DependencyHandlerScope.implementProject(value: String) {
    add("implementation", project(value))
}

context(VersionCatalog)
@Suppress("unused")
fun DependencyHandlerScope.implementKapt(value: String) {
    add("kapt", this@VersionCatalog.findLibrary(value).get())
}

context(VersionCatalog)
@Suppress("unused")
fun DependencyHandlerScope.implementKaptTest(value: String) {
    add("kaptTest", this@VersionCatalog.findLibrary(value).get())
}

context(VersionCatalog)
fun DependencyHandlerScope.implementKsp(value: String) {
    add("ksp", this@VersionCatalog.findLibrary(value).get())
}

context(VersionCatalog)
@Suppress("unused")
fun DependencyHandlerScope.implementAndroidTestDependency(value: String) {
    add("androidTestImplementation", this@VersionCatalog.findLibrary(value).get())
}

context(VersionCatalog)
@Suppress("unused")
fun DependencyHandlerScope.implementDebugImplementation(value: String) {
    add("debugImplementation", this@VersionCatalog.findLibrary(value).get())
}

context(VersionCatalog)
fun DependencyHandlerScope.implementTestDependency(value: String) {
    add("testImplementation", this@VersionCatalog.findLibrary(value).get())
}

context(VersionCatalog)
fun DependencyHandlerScope.implementTestRuntimeOnly(value: String) {
    add("testRuntimeOnly", this@VersionCatalog.findLibrary(value).get())
}