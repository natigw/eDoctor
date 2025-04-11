# eDoctor - Your health assistant

**eDoctor** is a modern Android application designed to simplify and enhance patient interactions. Built with Kotlin and following clean architecture principles, eDoctor supports dynamic localization, secure data handling, and smooth user experience using Jetpack components and dependency injection via Hilt, and more.

---

## 📱 Technologies used

- 🩺 Intuitive user interface for medical history and interactions
- 📦 Strict abstraction and maintainability with multi-modular design
- 🔐 Secure and optimized Android app architecture (MVI)
- 🧠 ViewModel-powered logic separation
- 💉 Modern dependency injection using Hilt
- 🌐 Multilingual support with runtime locale switching
- 🧪 Test-ready codebase with jUnit and Instrumented tests
- 📄 MobSF static codebase analysis
- Backend built with Ktor and deployed in AWS Cloud for easy remote access: **http://13.49.0.170:8080/docs**

---

## ▶️ How to run eDoctor?

Follow these instructions to set up the project in your local machine.

### Prerequisites

- Android Studio (2023.1 or newer)
- Android SDK 29+
- JDK 17+
- Gradle 8.x
- Internet connection to sync dependencies

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/natigw/eDoctor.git
   ```

2. **Open the project in Android Studio**

3. **Add local API keys**  
   Create a file named `apikeys.properties` inside the `eDoctor` module directory and put your Google Maps API key:
   ```properties
   API_KEY=your_api_key_here
   ```

4. **Sync Gradle**  
   Android Studio will prompt you to sync the Gradle files — accept it.

5. **Build and Run**  
   Choose a device or emulator and run the project.

Make sure your `apikeys.properties` file is excluded from version control, as defined in `.gitignore`.

---

## Screenshots from eDoctor
<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/93553baf-9b5f-44b1-b855-3cdd887cb42a" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/20c5e7dc-dc9a-4233-b804-316986e39b3b" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/a1838067-f28d-4844-af9a-755687c36803" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/b25a8722-737e-4093-871a-4a110e47d70c" width="200"/></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/9418ece7-d002-4914-b809-45fd4b4c8741" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/b79d949c-ccf9-4f88-a5da-851ce7b734be" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/9f2a4e05-6f07-4faa-839b-193487692d89" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/9036b052-c2fc-47e4-aaf9-fbb6416d4130" width="200"/></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/629bcc4f-4b5d-40dc-be58-f2a5e4d10898" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/ca89915e-0321-49ab-a2a8-18edbe50d722" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/df93c6da-3f39-4486-ac58-66fe20d9f388" width="200"/></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/5fdef994-c49e-4672-9aac-fc4e661a2e70" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/16b73a99-20d2-479d-b595-b4c7b523bc0b" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/080274b4-3a65-41cd-b575-3a2fbb77be24" width="200"/></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/52312b01-8e12-4daf-ae7a-ff10c55b1ce5" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/cb5f3b14-6300-4bdd-81f7-03c238dc7ccd" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/5fcf5f6a-4191-4738-a505-9d9f237d7854" width="200"/></td>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/84aed454-e1e4-4172-888f-107c4ad8b4cb" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/270802be-5937-457c-a14a-eb28673875f9" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/5a4f2c5f-bbb5-4768-a7de-adf8d18f0b44" width="200"/></td>
    <td><img src="https://github.com/user-attachments/assets/241d03d5-dcbf-46f7-bbbf-45704110bced" width="200"/></td>
  </tr>
</table>

---

## 👨‍💻 Authors

- [Natig Mammadov](https://github.com/natigw)
- [Vusat Orujov](https://github.com/kematian05)
- [Farid Guliyev](https://github.com/faridGuliyew)
