# 📱 Practical 3 — Implicit & Explicit Intents

An Android application demonstrating **Implicit and Explicit Intents** by launching system apps and a custom Login screen from a single UI.

---

## 🎯 Aim

Create an Android application which demonstrates **implicit & explicit Intent**.

---

## ✨ Features

| # | Action | Intent Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | 🌐 Browse | Implicit | Opens a URL entered by the user in a browser |
| 2 | 📞 Call | Implicit | Dials a phone number entered by the user |
| 3 | 📋 Call Log | Implicit | Opens the device's Call Log |
| 4 | 🖼️ Gallery | Implicit | Opens the Gallery to view images |
| 5 | 📷 Camera | Implicit | Opens the Camera app |
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

- Intent & types of Intent (**Implicit vs Explicit**)
- Types of Intent Action:
  - `ACTION_VIEW`
  - `ACTION_DIAL`
  - `ACTION_IMAGE_CAPTURE`
  - `ACTION_SHOW_ALARMS`
  - `ACTION_MAIN`
- `Intent.setData()` and `Intent.setType()` methods
- `Uri.parse()` method
- `startActivity()` method
- `ContactsContract.Contacts.CONTENT_TYPE`
- `CallLog.Calls.CONTENT_URI`
- MIME types — `"image/*"`
- URI schemes — `"tel:"`
- `Button`, `EditText`, `ConstraintLayout`
- Adding a new Activity to an Android project
- Adding Drawable resources to an Android project
- Registering Activities in `AndroidManifest.xml`

---

## 📂 Project Structure

```text
app/
└── src/main/
    ├── java/com/dev/a24012011080_mad_pr3/
    │   ├── MainActivity.kt
    │   │   └── Implicit + Explicit Intent triggers
    │   └── LoginActivity.kt
    │       └── Explicit Intent target screen
    │
    ├── res/
    │   └── layout/
    │       ├── activity_main.xml
    │       └── activity_login.xml
    │
    └── AndroidManifest.xml
