## Project Overview
This mobile application is a solution for the Yomicepa Mobile Coding Challenge 2024. It allows pharmacy owners to manage return requests for outdated items using the provided RxMax API.

## Features
- User authentication
- View list of return requests
- Create new return requests
- Add items to return requests
- View items in a return request
- Update items in a return request
- Delete items from a return request

## Technology Stack
- Platform: Android
- Language: Kotlin
- UI Framework: Jetpack Compose
- Networking: Retrofit
- Asynchronous Programming: Coroutines
- Dependency Injection: Hilt
- Architecture: MVVM with Clean Architecture principles

- ## Setup Instructions
1. Clone the repository
2. Open the project in Android Studio (Arctic Fox or later).
3. Sync the project with Gradle files.
4. Run the app on an emulator or physical device.

## API Configuration
The app uses the RxMax API. The base URL is already configured, but you may need to update it in the `ApiClient.kt` file if it changes:

```kotlin
private const val BASE_URL = "https://portal-test.rxmaxreturns.com/rxmax/"


Design Decisions

Jetpack Compose: Chosen for its modern declarative UI approach, which allows for faster development and easier maintenance.
MVVM Architecture: Provides a clear separation of concerns, making the codebase more maintainable and testable.
Coroutines: Used for managing asynchronous operations, providing a more straightforward and efficient way to handle API calls compared to callbacks.
Hilt: Simplifies dependency injection, reducing boilerplate code and making the app more modular.
Single Activity Architecture: Utilized for its simplicity and better integration with Jetpack Navigation.

Author
Ali Youssef 
