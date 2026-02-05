# Little Lemon - Android App Capstone

![Little Lemon](https://img.shields.io/badge/Platform-Android-green) ![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue) ![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-blue)

## About

**Little Lemon** is the final capstone project for the [Android App Development Capstone Course](https://www.coursera.org/learn/android-app-capstone) on Coursera. This project demonstrates the implementation of a modern Android restaurant application using cutting-edge Android technologies and best practices.

## Project Overview

Little Lemon is a restaurant discovery and ordering application that showcases:
- User authentication and onboarding
- Dynamic menu browsing with filtering and search
- User profile management
- Local data persistence with Room database
- Remote API integration
- Modern UI with Jetpack Compose

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Local Database**: Room Persistence Library
- **Networking**: Retrofit
- **State Management**: ViewModel & StateFlow
- **Dependency Injection**: Manual DI pattern
- **Build System**: Gradle (Kotlin DSL)

## Building & Running

### Prerequisites
- Android Studio 2024.1 or higher
- Android SDK 24 (API level 24) or higher
- Kotlin 1.9.0 or higher

### Build
```bash
./gradlew build
```

### Run
```bash
./gradlew installDebug
```

Or open the project in Android Studio and click the Run button.

### Run Tests
```bash
./gradlew test                    # Unit tests
./gradlew connectedAndroidTest   # Instrumented tests
```

## Coursera Course

This project is the capstone assignment for:
- **Course**: [Android App Development Capstone](https://www.coursera.org/learn/android-app-capstone)
- **Provider**: Coursera
- **Learning Objectives**:
  - Design and implement a complete Android application
  - Apply MVVM architecture pattern
  - Work with modern Android libraries and tools
  - Implement local and remote data persistence
  - Create responsive user interfaces with Compose
## License

This project is part of the Coursera Android App Development Capstone course.

## Author

Created as a capstone project for Android App Development on Coursera.

---

**Happy Coding! 🍋**
