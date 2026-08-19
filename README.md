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

## 🧩 Key Code Snippets

### Implicit Intent — Open URL (Browser)

```kotlin
val url = "https://www.example.com"
val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
startActivity(intent)
```

### Implicit Intent — Dial a Number (open Dialer)

```kotlin
val number = "1234567890"
val intent = Intent(Intent.ACTION_DIAL).apply {
    data = Uri.parse("tel:$number")
}
startActivity(intent)
```

Note: ACTION_DIAL opens the dialer with the number pre-filled and does not require CALL_PHONE permission. To place a call directly, use ACTION_CALL but you must request the CALL_PHONE runtime permission and declare it in the manifest.

```kotlin
// Requires Manifest permission: android.permission.CALL_PHONE
if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
    == PackageManager.PERMISSION_GRANTED) {
    val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
    startActivity(callIntent)
} else {
    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL)
}
```

### Implicit Intent — Open Call Log

```kotlin
val intent = Intent(Intent.ACTION_VIEW).apply {
    data = CallLog.Calls.CONTENT_URI
}
startActivity(intent)
```

This opens the device call log. Some devices may provide different behavior depending on OEM apps.

### Implicit Intent — Pick an Image from Gallery (Activity Result API)

```kotlin
// In an Activity or Fragment
private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
    uri?.let { selectedImageUri ->
        // Use the image URI (display in ImageView or upload)
        imageView.setImageURI(selectedImageUri)
    }
}

// To launch
pickImageLauncher.launch("image/*")
```

Alternate (legacy) using ACTION_PICK:

```kotlin
val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
startActivity(intent)
```

### Implicit Intent — Open Gallery to View Images

To simply open a viewer for images:

```kotlin
val intent = Intent(Intent.ACTION_VIEW).apply {
    type = "image/*"
}
startActivity(intent)
```

### Implicit Intent — Capture Photo with Camera (saving to a file)

Use the Activity Result API (TakePicture) with a FileProvider URI to save the full-size image:

```kotlin
// 1) Create a file for the photo
private fun createImageFile(): File {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
    return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
}

// 2) Register launcher
private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
    if (success) {
        // The image was saved to the provided URI — display or process it
        imageView.setImageURI(photoUri)
    }
}

// 3) To take photo
val photoFile = createImageFile()
val photoUri: Uri = FileProvider.getUriForFile(this, "${applicationContext.packageName}.fileprovider", photoFile)
takePictureLauncher.launch(photoUri)
```

Remember to add a FileProvider entry in AndroidManifest.xml and a file_paths.xml resource describing allowed paths.

### Implicit Intent — Open Camera (quick preview)

```kotlin
val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
startActivity(intent)
```

This returns a small bitmap in the result with the legacy API. For a full-size image, use a file + FileProvider as shown above.

### Implicit Intent — Show Alarms (Clock)

```kotlin
val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
startActivity(intent)
```

### Explicit Intent — Open Login Activity

```kotlin
val intent = Intent(this@MainActivity, LoginActivity::class.java)
startActivity(intent)
```

---

## Runtime Permissions — Examples (Activity Result APIs)

Use Activity Result contracts for permissions rather than the legacy onRequestPermissionsResult callback.

```kotlin
// Single permission
private val requestPermissionLauncher =
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            // Permission is granted. Continue the action that requires the permission.
        } else {
            // Explain to the user that the feature is unavailable without the permission.
        }
    }

// Requesting multiple permissions
private val requestMultiplePermissionsLauncher =
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions: Map<String, Boolean> ->
        // permissions map contains the result for each requested permission
    }

// To launch
requestPermissionLauncher.launch(Manifest.permission.CAMERA)
// or
requestMultiplePermissionsLauncher.launch(arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE))
```

Also declare the corresponding permissions in your AndroidManifest.xml (e.g., CAMERA, READ_EXTERNAL_STORAGE, CALL_PHONE) and follow Android's best practices for explaining why a permission is needed.

---

## ▶️ How to Run

1. Open the project in Android Studio.
2. Let Gradle sync complete.
3. Make sure you have declared required permissions in AndroidManifest.xml (e.g., CAMERA, READ_EXTERNAL_STORAGE, CALL_PHONE if using ACTION_CALL).
4. If using camera file saving, add a FileProvider entry in AndroidManifest.xml and create res/xml/file_paths.xml.
5. Build and run the app on an emulator or a physical device.
6. Tap each button and verify the corresponding app/screen opens:
   - Browse → Opens the entered URL in a browser
   - Call → Opens the Dialer with the entered number (or places a call with permission)
   - Call Log → Opens the device's Call Log
   - Gallery → Lets the user pick or view images
   - Camera → Captures an image
   - Alarm → Opens the Clock app's Alarm screen
   - Login → Navigates to the custom Login screen

Note: Some actions (Camera, Gallery, Call) may require runtime permissions depending on Android version and device. Handle permissions using the Activity Result APIs as shown above.

---

## 📸 Screenshots

Include screenshots in the `screenshots/` directory. Example:

- screenshots/ss1.png — Main Menu
- screenshots/ss2.png — Browser
- screenshots/ss3.png — Dialer
- screenshots/ss4.png — Call Log

You can embed images in this README using Markdown:

![Main Menu](screenshots/ss1.png)
![Browser](screenshots/ss2.png)

---

## 👤 Submitted By

**Name:** Krish Patel

**Enrollment No:** 24012011097

**Practical:** 03
