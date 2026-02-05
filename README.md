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

## Project Structure

```
app/src/main/java/com/example/littlelemon/
├── MainActivity.kt                 # Main entry point
├── MainApplication.kt              # Application class
├── navigation/
│   ├── AppNavigation.kt           # Navigation setup
│   ├── AppViewModel.kt            # Shared app-level ViewModel
│   └── Destinations.kt            # Route definitions
├── screens/
│   ├── home/                      # Home screen with menu & filtering
│   ├── onboarding/                # User registration & login
│   └── profile/                   # User profile management
├── data/
│   ├── api/                       # Remote API integration
│   ├── database/                  # Room database setup
│   ├── model/                     # Data models
│   ├── preferences/               # SharedPreferences for user data
│   └── repository/                # Data layer abstraction
└── ui/
    ├── components/                # Reusable UI components
    └── theme/                     # Design tokens & theming
```

## Features

### 🔐 Authentication & Onboarding
- User registration with email and password validation
- Secure login flow
- Persistent user session management
- First-time user onboarding experience

### 🍽️ Menu Browsing
- Display restaurant menu items from remote API
- Category filtering (Appetizers, Mains, Desserts, Drinks)
- Search functionality
- Menu item details and images
- Persistent local cache for offline access

### 👤 User Profile
- View and edit user information
- Account preferences
- Logout functionality

### 💾 Data Persistence
- Room database for local menu caching
- SharedPreferences for user session data
- Offline-first architecture

### 🔄 API Integration
- RESTful API client with Retrofit
- Dynamic data synchronization
- Error handling and retry logic

## Wireframe & App Flow

### Screen Navigation

```
┌─────────────────────────────────────────────┐
│          ONBOARDING FLOW                    │
└─────────────────────────────────────────────┘
         │
         ├─► Login Screen
         │   ├─ Email input
         │   ├─ Password input
         │   └─ Login button
         │
         └─► Registration Screen
             ├─ First name input
             ├─ Last name input
             ├─ Email input
             ├─ Password input
             └─ Register button
                    │
                    ▼
         ┌─────────────────────────────────────────────┐
         │      HOME SCREEN (Main Navigation)         │
         └─────────────────────────────────────────────┘
                    │
         ┌──────────┼──────────┐
         │          │          │
         ▼          ▼          ▼
    ┌────────┐ ┌────────┐ ┌────────┐
    │  HOME  │ │ PROFILE│ │  MENU  │
    └────────┘ └────────┘ └────────┘
```

### Home Screen Layout
```
┌─────────────────────────────────┐
│  HEADER                         │
│  ┌──────────────────────────┐   │
│  │ Little Lemon Restaurant  │   │
│  └──────────────────────────┘   │
├─────────────────────────────────┤
│  SEARCH & FILTERS               │
│  ┌──────────────────────────┐   │
│  │ 🔍 Search menu items...  │   │
│  └──────────────────────────┘   │
│  Categories: [All] [Appetizers] │
│              [Mains] [Desserts]  │
│              [Drinks]            │
├─────────────────────────────────┤
│  MENU ITEMS (Grid/List)         │
│  ┌──────────┬──────────┐        │
│  │ Item 1   │ Item 2   │        │
│  │ Image    │ Image    │        │
│  │ Price    │ Price    │        │
│  └──────────┴──────────┘        │
│  ┌──────────┬──────────┐        │
│  │ Item 3   │ Item 4   │        │
│  │ Image    │ Image    │        │
│  │ Price    │ Price    │        │
│  └──────────┴──────────┘        │
└─────────────────────────────────┘
```

### Profile Screen Layout
```
┌─────────────────────────────────┐
│  USER PROFILE                   │
├─────────────────────────────────┤
│  User Information               │
│  ┌──────────────────────────┐   │
│  │ First Name: [________]   │   │
│  │ Last Name:  [________]   │   │
│  │ Email:      [________]   │   │
│  └──────────────────────────┘   │
│                                 │
│  ┌──────────────────────────┐   │
│  │     Logout Button        │   │
│  └──────────────────────────┘   │
└─────────────────────────────────┘
```

## Key Components

### Database Schema
- **MenuItem**: Stores menu items (id, title, description, price, image, category)
- **User**: Stores user information (id, firstName, lastName, email, password)

### API Integration
- Endpoint: Fetches menu items from Little Lemon backend
- Data sync on app launch and periodic updates

### State Management
Each screen implements:
- **State class**: Immutable state representation
- **ViewModel**: Business logic and state management
- **Compose UI**: Reactive UI based on state

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

## Dependencies

- **Jetpack Compose**: Modern Android UI toolkit
- **Room**: Local database abstraction
- **Retrofit**: HTTP client
- **Hilt/Manual DI**: Dependency injection
- **Compose Navigation**: Navigation in Compose
- **Lifecycle**: ViewModel and LiveData

## Project Requirements Met

✅ User authentication (Login/Registration)
✅ Menu browsing with filtering and search
✅ User profile management
✅ Local data persistence with Room database
✅ Remote API integration with Retrofit
✅ MVVM architecture with ViewModel
✅ Jetpack Compose for UI
✅ Unit and instrumented tests
✅ Proper error handling
✅ Responsive design

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

## Future Enhancements

- 🛒 Add shopping cart functionality
- 💳 Payment integration
- ⭐ User reviews and ratings
- 🔔 Push notifications for new items
- 📊 Analytics integration
- 🌙 Dark mode support

## License

This project is part of the Coursera Android App Development Capstone course.

## Author

Created as a capstone project for Android App Development on Coursera.

---

**Happy Coding! 🍋**
