package com.monitech.favore_app.views

import android.content.Context
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Switch
import com.monitech.favore_app.R

class PreferencesActivity : BaseActivity() {
    private lateinit var smsSwitch: Switch
    private lateinit var whatsappSwitch: Switch
    private lateinit var emailSwitch: Switch
    private lateinit var pushNotificationsSwitch: Switch
    private lateinit var btnBack: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        loadUserAndConfigureNavBar()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        smsSwitch = findViewById(R.id.smsSwitch)
        whatsappSwitch = findViewById(R.id.whatsappSwitch)
        emailSwitch = findViewById(R.id.emailSwitch)
        pushNotificationsSwitch = findViewById(R.id.pushNotificationsSwitch)
        btnBack = findViewById(R.id.btnBack)

        loadPreferences()

        smsSwitch.setOnCheckedChangeListener { _, isChecked ->
            savePreferences()
        }

        whatsappSwitch.setOnCheckedChangeListener { _, isChecked ->
            savePreferences()
        }

        emailSwitch.setOnCheckedChangeListener { _, isChecked ->
            savePreferences()
        }

        pushNotificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            savePreferences()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun loadPreferences() {
        val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)

        smsSwitch.isChecked = sharedPreferences.getBoolean("sms_preference", false)
        whatsappSwitch.isChecked = sharedPreferences.getBoolean("whatsapp_preference", false)
        emailSwitch.isChecked = sharedPreferences.getBoolean("email_switch", false)
        pushNotificationsSwitch.isChecked = sharedPreferences.getBoolean("pushnotifications_switch", false)
    }

    private fun savePreferences() {
        val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putBoolean("sms_preference", smsSwitch.isChecked)
        editor.putBoolean("whatsapp_preference", whatsappSwitch.isChecked)
        editor.putBoolean("email_switch", emailSwitch.isChecked)
        editor.putBoolean("pushnotifications_switch", pushNotificationsSwitch.isChecked)

        editor.apply()
    }
}