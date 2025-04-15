# dev.devlight.skeleton

## Overview

This module encapsulates common build configurations, conventions, and standards, promoting
uniformity and best practices throughout the development process.

To improve code maintenance, a number of plugins have been developed.

They are grouped by subject area:

- Android application [+Compose];
- Android features [+Compose];
- Android tests (aka integration tests);
- Kotlin library;
- Unit tests;
- some other plugins for code smell reducing.

## Implementation details

### Convention plugins

#### Android

##### AndroidApplicationConventionPlugin/AndroidApplicationComposeConventionPlugin

Facilitates the development of Android applications, with built-in support for Jetpack Compose.

##### AndroidFeatureConventionPlugin/AndroidFeatureComposeConventionPlugin

Supports the creation of modular Android features, incorporating Jetpack Compose for modern UI
development.

##### AndroidTestConventionPlugin

Provides a robust setup for integration testing, ensuring that different parts of your application
work seamlessly together.

##### AppDistributionConventionPlugin

Provides a robust setup for using Firebase AppDistribution.

##### HiltConventionPlugin

Simplifies the integration of Dagger Hilt for dependency injection in Android projects.

Ensures a consistent setup and usage of Hilt across all modules, promoting better dependency
management and code modularity.

#### Kotlin

##### KotlinLibraryConventionPlugin

Streamlines the development and management of Kotlin libraries, promoting code reuse and
consistency.

##### KotlinTestConventionPlugin

Enhances the setup and execution of unit tests, ensuring code correctness and reliability.

#### Common

##### DetektConventionPlugin

Integrates Detekt for Kotlin static code analysis, enforcing coding standards and detecting
potential issues early in the development cycle.
Customizable rules and configurations to match your project’s specific requirements.

##### KtlintConventionPlugin

Incorporates Ktlint for Kotlin code formatting, ensuring a consistent coding style across your
project.
Automatically formats code and highlights deviations from the defined coding standards.

##### DokkaConventionPlugin

Facilitates the use of Dokka for generating documentation from Kotlin code.
Ensures consistent documentation practices, making it easier to maintain and understand your
codebase.

### Project Configuration

All stuff for project-level configuration are located in:

- [ProjectConfig]();
- [VersionConfig]().

### Custom tasks

// TODO: JTBD

## References

## TODO

- [ ] custom tasks;
- [ ] links;
- [ ] describe plugins;
- [ ] describe all other stuff;
- [ ] add custom dir for compose-compiler-report-generator;