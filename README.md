# Rick and Morty Android App

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material3-4285F4?logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Android](https://img.shields.io/badge/minSdk-26-brightgreen?logo=android&logoColor=white)](https://developer.android.com/)
[![Build](https://github.com/AnnikenJE/rick-and-morty-android/actions/workflows/android.yml/badge.svg)](https://github.com/AnnikenJE/rick-and-morty-android/actions/workflows/android.yml)

A native Android app for browsing characters from the [Rick and Morty API](https://rickandmortyapi.com/), creating your own custom characters, and storing them locally with Room. Built with Kotlin and Jetpack Compose following an MVVM architecture.

## Features

- **Browse** — paginated list of official Rick and Morty characters fetched from the public API
- **Create** — build your own character and save it to a local Room database
- **Collection** — view and manage the characters you've created
- **Surprise me** — fetch a random character on demand
- Loading states, error handling, and image caching throughout

## Tech Stack

| Layer | Tools |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Navigation | Navigation Compose (type-safe routes) |
| Networking | Retrofit, OkHttp, Gson, kotlinx.serialization |
| Local storage | Room |
| Images | Coil |
| Architecture | MVVM (Screen → ViewModel → Repository) |

## Architecture

```
screens/<feature>/        UI (Composables) + ViewModel per feature
data/services/            Retrofit API interface + remote repository
data/database/            Room DAO, database, local repository
data/dataclasses/         API/DB models
navigation/               NavHost + bottom navigation
```

## Getting Started

**Requirements:** Android Studio (Koala or newer), JDK 17, Android SDK 34.

```bash
git clone git@github.com:AnnikenJE/rick-and-morty-android.git
cd rick-and-morty-android
./gradlew assembleDebug
```

Open the project in Android Studio and run on an emulator or device (min SDK 26).

## Tests

```bash
./gradlew test          # unit tests
./gradlew connectedCheck # instrumented tests (requires device/emulator)
```

## Background

Originally built as the exam project for **Android Programming (7.5 ECTS)** — Kristiania University College, graded **A**. 
