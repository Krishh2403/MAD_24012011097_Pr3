package com.example.mad_24012011097_pr3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CallLog
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Web URL - Browse
        val btnBrowse = findViewById<Button>(R.id.btn_browse)
        val etWebUrl = findViewById<EditText>(R.id.etWebUrl)
        btnBrowse.setOnClickListener {
            val url = etWebUrl.text.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        // 2. Phone No - Call
        val btnCall = findViewById<Button>(R.id.btn_call)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        btnCall.setOnClickListener {
            val phoneNo = etPhone.text.toString()
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNo"))
            startActivity(intent)
        }

        // 3. Call Log
        val btnCallLog = findViewById<Button>(R.id.btn_calllog)
        btnCallLog.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(CallLog.Calls.CONTENT_URI)
            startActivity(intent)
        }

        // 4. Gallery
        val btnGallery = findViewById<Button>(R.id.btn_gallary)
        btnGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivity(intent)
        }

        // 5. Camera
        val btnCamera = findViewById<Button>(R.id.btn_camera)
        btnCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        // 6. Alarm
        val btnAlarm = findViewById<Button>(R.id.btn_alarm)
        btnAlarm.setOnClickListener {
            val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
            startActivity(intent)
        }

        // 7. Login
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
