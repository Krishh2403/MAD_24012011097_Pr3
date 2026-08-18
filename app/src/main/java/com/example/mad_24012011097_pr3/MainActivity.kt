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

        val btnBrowse = findViewById<Button>(R.id.btn_browse)
        val etWebUrl = findViewById<EditText>(R.id.etWebUrl)
        btnBrowse.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(etWebUrl.text.toString()))
            startActivity(intent)
        }

        val btnCall = findViewById<Button>(R.id.btn_call)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${etPhone.text}"))
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_calllog).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.type = CallLog.Calls.CONTENT_TYPE
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_gallary).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.type = "image/*"
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_camera).setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_alarm).setOnClickListener {
            val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
