# 📱 Practical 3 — Implicit & Explicit Intents

An Android application demonstrating **Implicit and Explicit Intents** by launching system apps and a custom Login screen from a single UI.

---

## 🎯 Aim

Create an Android application which demonstrates **implicit and explicit intents**.

---

## ✨ Features

| # | Action | Intent Type | Description |
| :-: | --- | --- | --- |
| 1 | 🌐 Browse | Implicit | Opens a URL entered by the user in a browser |
| 2 | 📞 Call | Implicit | Opens the Dialer with a phone number entered by the user (or places a call with permission) |
| 3 | 📋 Call Log | Implicit | Opens the device's Call Log |
| 4 | 🖼️ Gallery | Implicit | Lets the user pick or view images from Gallery |
| 5 | 📷 Camera | Implicit | Captures an image using the Camera app and optionally saves it |
| 6 | ⏰ Alarm | Implicit | Opens the Clock app's Alarms screen |
| 7 | 🔐 Login | Explicit | Navigates to a custom `LoginActivity` |

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **Layout:** ConstraintLayout
- **Min SDK / Target SDK:** As configured in `build.gradle`
- **IDE:** Android Studio

---

## 📚 Study Concepts Covered

- Intents (Implicit vs Explicit)
- Common Intent actions: `ACTION_VIEW`, `ACTION_DIAL`, `ACTION_CALL`, `ACTION_IMAGE_CAPTURE`, `ACTION_PICK`, `ACTION_MAIN`, `AlarmClock.ACTION_SHOW_ALARMS`
- `Intent.setData()` and `Intent.setType()`
- `Uri.parse()` and URI schemes like `tel:`
- `startActivity()` and the Activity Result APIs
- Runtime permissions and how to request them at runtime
- `CallLog.Calls.CONTENT_URI` and `ContactsContract`
- MIME types (e.g., `image/*`)
- Android UI elements: `Button`, `EditText`, `ConstraintLayout`
- Adding Activities and drawables to a project
- Registering Activities and `FileProvider` in `AndroidManifest.xml`

---

## 📂 Project Structure

```text
app/
└── src/main/
    ├── java/com/dev/a24012011080_mad_pr3/
    │   ├── MainActivity.kt        # Implicit + Explicit intent triggers
    │   └── LoginActivity.kt       # Explicit intent target screen
    ├── res/
    │   └── layout/
    │       ├── activity_main.xml
    │       └── activity_login.xml
    └── AndroidManifest.xml
```

---

🧩 Key Code Snippets

Implicit Intent — Open URL (Browser)

```kotlin
val url = "https://www.example.com"
val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
startActivity(intent)
```

Implicit Intent — Dial a Number

```kotlin
val number = "1234567890"
val intent = Intent(Intent.ACTION_DIAL).apply {
    data = Uri.parse("tel:$number")
}
startActivity(intent)
```

Implicit Intent — Open Gallery (view images)

```kotlin
val intent = Intent(Intent.ACTION_VIEW).apply {
    type = "image/*"
}
startActivity(intent)
```

Implicit Intent — Open Camera

```kotlin
val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
startActivity(intent)
```

Implicit Intent — Show Alarms (Clock)

```kotlin
val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
startActivity(intent)
```

Explicit Intent — Open Login Activity

```kotlin
val intent = Intent(this@MainActivity, LoginActivity::class.java)
startActivity(intent)
```

▶️ How to Run
Open the project in Android Studio.

Let Gradle sync complete.

Run on an emulator or physical device (Pixel 8 / API 30+ recommended).

Tap each button and verify the corresponding app/screen opens:

Browse → Opens the entered URL in a browser

Call → Opens the Dialer with the entered number

Call Log → Opens the device's Call Log

Gallery → Opens the Gallery app

Camera → Opens the Camera app

Alarm → Opens the Clock app's Alarm screen

Login → Navigates to the custom Login screen

⚠️ Note: Some actions (Call, Camera, Gallery) may require runtime permissions depending on the Android version.

📸 Screenshots


| Main Menu | Browser | Dialer | Call Log |
| :---: | :---: | :---: | :---: |
| ![Main Menu](screenshots/ss1.png) | ![Browser](screenshots/ss2.png) | ![Dialer](screenshots/ss3.png) | ![Call Log](screenshots/ss4.png) |
| **Gallery** | **Camera** | **Alarms** | **Login** |
| ![Gallery](screenshots/ss5.png) | ![Camera](screenshots/ss6.png) | ![Alarms](screenshots/ss7.png) | ![Login](screenshots/ss8.png) |

👤 Submitted By
Name: Krish Patel

Enrollment No: 24012011097

Practical: 03
