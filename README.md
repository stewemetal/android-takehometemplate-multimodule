# Android Take Home Template - Multi-Module

A modern Android take-home template project demonstrating best practices with multi-module architecture, Jetpack Compose, and automated AI assistance.

## 🏗️ Architecture

This project uses a clean multi-module architecture:

- **`:app`** - Main application module
- **`:shell`** - Core infrastructure (networking, database, DI)
- **`:library:design`** - Shared UI components and design system
- **Feature Modules** - Split into `:contract` (public API) and `:impl` (implementation)
  - `:feature:login`
  - `:feature:item-list`
  - `:feature:item-details`

## 🚀 Tech Stack

- **UI**: Jetpack Compose with Material3
- **Navigation**: Jetpack Navigation Compose
- **DI**: Koin with KSP annotation processing
- **Networking**: Retrofit + OkHttp + Moshi
- **Database**: Room
- **Build**: Gradle with custom convention plugins
- **Code Quality**: Detekt

## 🤖 AI-Powered Development

This repository includes **Claude Code automation** for issue-based development:

1. Create an issue with the `claude-code` label
2. Claude Code automatically implements it
3. Review the generated PR

[Learn more about Claude Code automation →](.github/CLAUDE_CODE_SETUP.md)

## 🛠️ Build Commands

### Basic Operations
```bash
# Build the project
./gradlew build

# Run tests
./gradlew test

# Install debug build
./gradlew installDebug
```

### Code Quality
```bash
# Run Detekt
./gradlew detekt
```

See [CLAUDE.md](CLAUDE.md) for detailed build instructions and architecture documentation.

## 📋 Requirements

- JDK 17
- Android SDK 36
- Gradle 8.14+

## 🚀 Quick Start

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle
4. Run the app

## 🤝 Contributing

### Using Claude Code

For new features or fixes, create an issue with the `claude-code` label and let AI handle the implementation!

### Manual Development

1. Create a feature branch
2. Follow the existing architecture patterns
3. Add tests
4. Submit a PR

## 📚 Documentation

- [Build Commands & Architecture](CLAUDE.md)
- [Claude Code Setup](.github/CLAUDE_CODE_SETUP.md)

## 📄 License

[Add your license here]
