
# Bimm KMP Challenge

A multiplatform mobile application built with **Kotlin Multiplatform** (KMP), displaying a list of sake shops with images, ratings, locations, and helpful links. The project shares business logic across Android and iOS to reduce code duplication and increase maintainability.

## 🧱 Project Structure

- **shared**: Common KMP module with business logic, models, repositories, and JSON loading.
- **androidApp / composeApp**: Android app written using Jetpack Compose.
- **iosApp**: iOS app written in SwiftUI, integrating the `shared` module via Kotlin Native.

## 🛠️ Technologies & Libraries

- **Kotlin Multiplatform**
- **Jetpack Compose** for Android UI
- **SwiftUI** for iOS UI
- **Koin** for dependency injection (Android only)
- **Kotlinx Serialization** for JSON parsing
- **Turbine** + **kotlinx.coroutines.test** for unit testing
- **Coil** for image loading on Android
- **AsyncImage** for remote image loading on iOS

## 🔄 Architecture

- MVVM-like architecture: `ViewModel` → `State` → `UI`
- `SakeShopsRepository` abstracts data sources
- `SakeShopDatasource` loads from a local JSON file (`sakeshop.json`)
- On Android, dependency injection is handled with **Koin**
- On iOS, Koin was replaced with a manual factory (`SharedFactory`) due to compatibility issues

## 📱 Features

- Sake shops list view
- Detail screen with:
  - Featured image
  - Address and rating
  - Description
  - Website and map links

## 🧪 Unit Testing

### Shared Module

- DTO to Domain mapping
- `SakeShopLocalDatasource` with `JsonProvider`
- `SakeShopRepositoryImpl`
- `SharedFactory`

### Android

- `SakeShopsViewModel` tested with `Turbine` and fake repository

> A basic iOS ViewModel test was added. UI tests were not included due to time constraints.

## ⚠️ Notes

- **Koin was not integrated in iOS**: Attempts to use `startKoin` from Swift failed due to native binding limitations and time constraints. `SharedFactory` was used instead.
- **JSON loading on iOS** is handled from the app bundle directly because the initial intent to centralize it in `shared` was not feasible under current KMP limitations.
- The property `description_` was used in Swift to avoid naming collisions with `NSObject.description`.

## ⌛ Time & Scope Considerations

- Priority was given to delivering a functional cross-platform solution.
- Some architectural choices (e.g. multiple datasources) were simplified to fit the project timeline.
- Both UIs were aligned visually with slight platform-specific adaptations.

## 📂 Resources

- `sakeshop.json`: source data for the app.
- Images: handled via Coil (Android) and `AsyncImage` (iOS). A local placeholder image named `sake` is provided.

## 🚀 Running the App

### Android

```bash
./gradlew composeApp:installDebug
```

### iOS
# Build shared framework for simulator
```bash
./gradlew :shared:linkReleaseFrameworkIosSimulatorArm64
```
# Open default simulator
```bash
open -a Simulator
```

# Move into iOS project
```bash
cd iosApp
```

# Build the iOS app for simulator
```bash
xcodebuild -project iosApp.xcodeproj -scheme iosApp -configuration Debug -sdk iphonesimulator -derivedDataPath build build
```

# Find the app path (first .app found)
```bash
APP_PATH="$(find build -type d -name '*.app' | head -n1)"
```

# Install on the currently booted simulator
```bash
xcrun simctl install booted "$APP_PATH"
```

# Get the Bundle ID from Info.plist
```bash
BUNDLE_ID=$(/usr/libexec/PlistBuddy -c 'Print :CFBundleIdentifier' "$APP_PATH/Info.plist")
```

# Launch the app in the simulator
```bash
xcrun simctl launch booted "$BUNDLE_ID"
```
