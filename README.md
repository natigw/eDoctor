# eDoctor

**eDoctor** is a modern Android application designed to simplify and enhance doctor-patient interactions. Built with Kotlin and following clean architecture principles, eDoctor supports dynamic localization, secure data handling, and smooth user experience using Jetpack components and dependency injection via Hilt.

---

## 📱 Features

- 🩺 Intuitive user interface for medical appointments and interactions
- 🌐 Multilingual support with runtime locale switching
- 🔐 Secure and optimized Android app architecture (MVVM)
- 🧠 ViewModel-powered logic separation
- 📦 Modern dependency injection using Hilt
- 📄 Gradle Kotlin DSL and modularized structure
- 🧪 Test-ready codebase with Room/Retrofit (if applicable)

---

## 🛠 Tech Stack

| Layer             | Technology        |
|------------------|-------------------|
| Language          | Kotlin            |
| Architecture      | MVVM              |
| DI Framework      | Hilt              |
| Android Components| Jetpack (ViewModel, LiveData, etc.) |
| Build System      | Gradle Kotlin DSL |
| Localization      | Custom LocaleHelper |
| Testing (Planned) | JUnit, Espresso   |

---

## 🚀 Getting Started

Follow these instructions to set up the project locally.

### Prerequisites

- Android Studio Hedgehog (2023.1) or newer
- Android SDK 33+
- JDK 17
- Gradle 8.x
- Internet connection to sync dependencies

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/eDoctor.git
   ```

2. **Open the project in Android Studio**

3. **Add local API keys (if required)**  
   Create a file named `apikeys.properties` inside the `eDoctor` module directory:
   ```properties
   API_KEY=your_api_key_here
   ```

4. **Sync Gradle**  
   Android Studio will prompt you to sync the Gradle files — accept it.

5. **Build and Run**  
   Choose a device or emulator and run the project.

---

## 🔐 Environment Variables

The following environment variables are required:

| Variable   | Description        |
|------------|--------------------|
| API_KEY    | Your backend/API key (if used) |

Make sure your `apikeys.properties` file is excluded from version control, as defined in `.gitignore`.

---

## 🧪 Testing (Coming Soon)

The app is structured for future integration with:

- **Unit Testing:** `JUnit4`, `Mockito`
- **UI Testing:** `Espresso`, `UI Automator`

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/my-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/my-feature`)
5. Open a pull request

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Authors

- [Natig Mammadov](https://github.com/natigw)
- [Vusat Orujov](https://github.com/kematian05)
- [Farid Guliyev](https://github.com/faridGuliyew)
---
