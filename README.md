# NewsByte 📰


![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue.svg?style=flat&logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-4CAF50?logo=android)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20%7C%20MVVM-orange)
![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat)

An elegant, fast-paced news summary application built with modern Android development practices. Inspired by the "InShorts" UX, NewsByte delivers paginated, bite-sized news updates using a strictly-typed, highly scalable architecture.

## 📱 Visuals

<img width="108" height="240" alt="Screenshot_20260214_164716" src="https://github.com/user-attachments/assets/9abe8575-4df5-4454-9b93-7a8367bbaee0" />
<img width="108" height="240" alt="Screenshot_20260214_164711" src="https://github.com/user-attachments/assets/69cdd0be-0edf-4d12-8858-69037b60521d" />
<img width="108" height="240" alt="Screenshot_20260214_164555" src="https://github.com/user-attachments/assets/1b8f136a-d9f1-4d2d-b1bc-7d4b1789da97" />
<img width="108" height="240" alt="Screenshot_20260214_164538" src="https://github.com/user-attachments/assets/9cc0d7d3-d914-402c-990f-1b0112da4029" />
<img width="108" height="240" alt="Screenshot_20260214_164527" src="https://github.com/user-attachments/assets/cdf919c9-bf9e-4594-be49-24b5e4c7308b" />
<img width="108" height="240" alt="Screenshot_20260214_164523" src="https://github.com/user-attachments/assets/ed4a8142-2da2-4ec6-8273-c1bbc0eb7693" />
<img width="108" height="240" alt="Screenshot_20260214_164509" src="https://github.com/user-attachments/assets/90d25e5f-4e40-4c10-b016-31328868046e" />
<img width="108" height="240" alt="Screenshot_20260214_164503" src="https://github.com/user-attachments/assets/6d7ab42b-449f-48ca-868c-291adadad6b6" />

---

## 🏗 Architecture & Technical Decisions



This project strictly adheres to **Clean Architecture** and **MVVM** principles to separate concerns, making the codebase scalable, testable, and maintainable. 

* **Presentation Layer:** Jetpack Compose, MVVM, and State Hoisting. Completely decoupled from data sources.
* **Domain Layer:** Pure Kotlin data models (`Article`) and repository interfaces.
* **Data Layer:** DTO mapping, OkHttp Interceptors, and Paging3 network routing.

**Design Philosophy:**
By intercepting raw, unreliable API JSONs at the Data layer and mapping them to domain-specific models, the UI remains completely shielded from network irregularities.

---

## ✨ Key Features

* **Infinite Vertical Paging:** TikTok/Reels-style scrolling utilizing Compose's `VerticalPager` seamlessly integrated with `Paging 3`.
* **Premium UI/UX:** Built on Material Design 3 featuring native Dark/Light mode, custom local typography (Playfair Display & Inter), and immersive edge-to-edge layouts.
* **Custom Shimmer Loading:** Hand-built Compose `Modifier` simulating light reflection to reduce perceived loading times.
* **Defensive Networking:** Custom OkHttp Interceptors handle API key injection securely, keeping keys entirely out of the UI and API service interfaces.
* **In-App Browsing:** Uses Chrome Custom Tabs for reading full articles without leaving the app context, complete with standard intent fallbacks.

---

## 🛠 Tech Stack

* **UI:** Jetpack Compose, Material 3
* **Architecture:** MVVM, Clean Architecture
* **Dependency Injection:** Dagger-Hilt
* **Networking:** Retrofit2, OkHttp3, kotlinx.serialization
* **Pagination:** Paging 3
* **Image Loading:** Coil
* **Asynchronous Programming:** Kotlin Coroutines & Flow
* **Desugaring:** Core Library Desugaring for backward compatibility with `java.time` on older Android versions.

---

## 🚀 Getting Started

To build and run this project locally, you must supply your own API key to authenticate the network requests.

1. Clone the repository:
2. Obtain a free API key from GNews API.
3. Create a local.properties file in the root directory of the project.
4. Add your API key to the file exactly like this: NEWS_API_KEY=your_api_key_here
* (Note: The Gradle build scripts are configured to read this file and inject it securely via BuildConfig. The local.properties file is git-ignored to prevent credential leaks).
5. Sync the project with Gradle files and run the app.

---

## Future Roadmap
* Single Source of Truth (SSOT): Implementing Room Database alongside a RemoteMediator to cache network requests and establish a true offline-first architecture.
* Unit Testing: Integrating MockK and JUnit for comprehensive coverage of the Domain and Presentation layers.
