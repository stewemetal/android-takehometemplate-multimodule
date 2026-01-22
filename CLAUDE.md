# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a multi-module Android take-home template project demonstrating modern Android architecture with Jetpack Compose, using Kotlin, Gradle version catalogs, and custom convention plugins.

## Build Commands

### Basic Build Operations
```bash
# Build the entire project
./gradlew build

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Clean build artifacts
./gradlew clean
```

### Testing
```bash
# Run all unit tests
./gradlew test

# Run unit tests for specific module
./gradlew :app:test
./gradlew :feature:login:impl:test

# Run Android instrumented tests
./gradlew connectedAndroidTest

# Run tests for a specific module
./gradlew :app:connectedDebugAndroidTest
```

### Code Quality
```bash
# Run detekt static analysis on all modules
./gradlew detekt

# Run detekt on specific module
./gradlew :app:detekt

# Detekt config is located at: build-config/detekt-config.yml
```

### Installation
```bash
# Install debug build on connected device
./gradlew installDebug
```

## Architecture

### Module Structure

The project follows a multi-module architecture with strict dependency rules:

1. **`:app`** - Main application module
   - Depends on `:shell` and all feature modules
   - Contains `MainActivity`, `TakeHomeTemplateApplication`, and `TakeHomeTemplateApp` (root Composable)
   - Configures Koin DI with all feature modules

2. **`:shell`** - Core infrastructure module
   - Provides shared infrastructure: networking (Retrofit/OkHttp), database (Room), dependency injection setup
   - Contains `ShellModule` for Koin DI with Retrofit, OkHttp, Moshi, Room database, and DispatcherProvider
   - Features depend on this module for shared utilities and architecture components

3. **`:library:design`** - Design system module
   - Contains shared UI components, themes, and design tokens

4. **Feature Modules** - Each feature is split into two modules:
   - **`:feature:<name>:contract`** - Public API (navigation interfaces, routes, extension functions)
   - **`:feature:<name>:impl`** - Implementation (screens, ViewModels, internal logic)

   Current features:
   - `login` - User authentication feature
   - `item-list` - List view of items
   - `item-details` - Detail view for individual items

### Dependency Rules

- Features can only depend on `:shell` and their own `:contract` module
- Features cannot depend on other feature modules (only their contracts via `:app`)
- The `:app` module wires everything together via navigation factories
- Contract modules expose navigation via `NavGraphFactory` interfaces

### Navigation Architecture

Navigation is decoupled using the factory pattern:

1. Each feature's contract module defines:
   - A route constant (e.g., `LoginRoute = "login"`)
   - A `NavGraphFactory` interface
   - Extension functions for navigation (e.g., `NavController.navigateToLogin()`)

2. Each feature's impl module:
   - Implements the `NavGraphFactory` interface
   - Provides the implementation via Koin DI

3. The `:app` module:
   - Injects all `NavGraphFactory` implementations
   - Builds the complete navigation graph in `TakeHomeTemplateApp.kt`
   - Includes debounced navigation helper to prevent double-clicks

### Dependency Injection

The project uses **Koin with KSP annotations**:

- Each module has a `*Module` class annotated with `@Module` and `@ComponentScan`
- Use `@Single` for singletons, `@Factory` for transient dependencies
- All modules are registered in `TakeHomeTemplateApplication.onCreate()`
- Generated Koin modules are accessed via `.module` extension (e.g., `ShellModule().module`)

### Build Logic

Custom Gradle convention plugins are defined in `build-logic/convention/`:

- **`project.application`** - Base Android application configuration
- **`project.application.compose`** - Compose setup for applications
- **`project.application.koin.annotation`** - Koin annotation processing for applications
- **`project.feature`** - Feature module configuration (includes library + compose + shell dependency)
- **`project.library`** - Android library configuration
- **`project.library.base`** - Base library without opinionated setup
- **`project.library.compose`** - Compose setup for libraries
- **`project.library.koin.annotation`** - Koin annotation processing for libraries

These plugins centralize common configuration and are applied in module `build.gradle.kts` files using:
```kotlin
plugins {
    alias(libs.plugins.project.feature)
    alias(libs.plugins.project.library.koin.annotation)
}
```

### Version Catalog

All dependencies are managed via Gradle version catalog (`gradle/libs.versions.toml`):
- Versions are defined in `[versions]` section
- Libraries in `[libraries]` section
- Plugins in `[plugins]` section
- Bundles group related dependencies (e.g., `compose`, `retrofit`, `room`, `moshi`)

### Key Technology Stack

- **UI**: Jetpack Compose with Material3
- **Navigation**: Jetpack Navigation Compose
- **DI**: Koin with KSP annotation processing (`@Module`, `@ComponentScan`, `@Single`, `@Factory`)
- **Networking**: Retrofit + OkHttp + Moshi
- **Database**: Room
- **Logging**: Timber
- **Code Quality**: Detekt with custom configuration
- **Build**: Gradle 8.14 with Kotlin DSL and custom convention plugins

## Common Development Tasks

### Adding a New Feature Module

1. Create two modules: `:feature:<name>:contract` and `:feature:<name>:impl`
2. In `settings.gradle.kts`, add:
   ```kotlin
   include(":feature:<name>:contract")
   include(":feature:<name>:impl")
   ```
3. Contract module `build.gradle.kts`:
   ```kotlin
   plugins {
       alias(libs.plugins.project.feature)
       alias(libs.plugins.project.library.koin.annotation)
   }
   ```
4. Create `NavGraphFactory` interface in contract with route constant
5. Impl module depends on contract:
   ```kotlin
   dependencies {
       implementation(project(":feature:<name>:contract"))
   }
   ```
6. Create `<Name>Module` class with `@Module` and `@ComponentScan` annotations
7. Register module in `TakeHomeTemplateApplication.kt`
8. Wire navigation in `TakeHomeTemplateApp.kt`

### Modifying Network Configuration

Edit `/Users/stewe/Library/CloudStorage/Dropbox/SoftwareProjects/Personal/TakeHomeTemplate_multimodule/shell/src/main/java/com/stewemetal/takehometemplate/shell/ShellModule.kt`:
- Change base URL in `retrofit()` function
- Add interceptors to `httpClient()` function
- Modify timeouts in `httpClient()` function

### Working with the Database

Database is defined in `shell/src/main/java/com/stewemetal/takehometemplate/shell/database/TakeHomeTemplateDatabase.kt`. To make changes:
1. Update entities in `shell/src/main/java/com/stewemetal/takehometemplate/shell/database/entity/`
2. Add DAOs to the database class
3. Room will handle code generation via KSP

### Detekt Configuration

Custom Detekt rules are in `/Users/stewe/Library/CloudStorage/Dropbox/SoftwareProjects/Personal/TakeHomeTemplate_multimodule/build-config/detekt-config.yml`:
- Composable functions are excluded from naming rules (line 336)
- Max line length is 120 characters (line 646)
- Forbidden comments include `TODO:`, `FIXME:`, `STOPSHIP:` (lines 577-582)
- Many style rules are disabled to allow flexibility
