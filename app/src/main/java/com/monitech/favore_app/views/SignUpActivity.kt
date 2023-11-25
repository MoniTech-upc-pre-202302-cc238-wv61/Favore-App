package com.monitech.favore_app.views

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.dto.UserCreateDTO
import com.monitech.favore_app.services.UserService

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
        val storedUser = Gson().toJson(null)

        val userService = UserService()

        var selected = "";

        val btnReturn: ImageButton = findViewById(R.id.btnReturn)
        btnReturn.setOnClickListener(){
            finish()
        }

        val btnSignIn: Button = findViewById(R.id.btnSignIn)
        btnSignIn.setOnClickListener(){
            val instance = Intent(this, SignInClientActivity::class.java)
            startActivity(instance)
        }

        val btnClient: Button = findViewById(R.id.btnClient)
        val btnFreelancer: Button = findViewById(R.id.btnFreelancer)

        btnClient.setOnClickListener(){
            //change the color of the button
            btnClient.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.md_theme_light_secondary));
            btnClient.setTextColor(resources.getColor(R.color.white));
            if(selected=="FREELANCER"){
                btnFreelancer.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white));
                btnFreelancer.setTextColor(resources.getColor(R.color.md_theme_light_secondary));
            }
            selected = "CLIENT";
        }

        btnFreelancer.setOnClickListener(){
            btnFreelancer.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.md_theme_light_secondary));
            btnFreelancer.setTextColor(resources.getColor(R.color.white));
            if(selected=="CLIENT"){
                btnClient.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white));
                btnClient.setTextColor(resources.getColor(R.color.md_theme_light_secondary));
            }
            selected = "FREELANCER";
        }

        val firstNameTextField: TextInputLayout = findViewById(R.id.firstNameTextField)
        val lastNameTextField: TextInputLayout = findViewById(R.id.lastNameTextField)
        val emailTextField: TextInputLayout = findViewById(R.id.emailTextField)
        val passwordTextField: TextInputLayout = findViewById(R.id.passwordTextField)

        val avatars = arrayOf(
            "https://cdn-icons-png.flaticon.com/128/4140/4140047.png",
            "https://cdn-icons-png.flaticon.com/128/4140/4140061.png",
            "https://cdn-icons-png.flaticon.com/128/4139/4139951.png",
            "https://cdn-icons-png.flaticon.com/128/4139/4139993.png",
            "https://cdn-icons-png.flaticon.com/128/4140/4140060.png",
            "https://cdn-icons-png.flaticon.com/128/4139/4139948.png",
            "https://cdn-icons-png.flaticon.com/128/4202/4202835.png",
            "https://cdn-icons-png.flaticon.com/128/145/145843.png",
            "https://cdn-icons-png.flaticon.com/128/4140/4140077.png",
            "https://cdn-icons-png.flaticon.com/128/4140/4140059.png",
            "https://cdn-icons-png.flaticon.com/128/4139/4139967.png",
            "https://cdn-icons-png.flaticon.com/128/3135/3135715.png"
        )

        val randomAvatar = avatars.random()

        val btnSignUp: Button = findViewById(R.id.btnSignUp)
        btnSignUp.setOnClickListener(){
            val userCreateDTO = UserCreateDTO(
                firstNameTextField.editText?.text.toString(),
                lastNameTextField.editText?.text.toString(),
                emailTextField.editText?.text.toString(),
                passwordTextField.editText?.text.toString(),
                randomAvatar,
                true,
                selected
            )

            userService.createUser(userCreateDTO) { user, message ->
                if (user != null) {
                    val storedUser = Gson().toJson(user)
                    val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putString("user", storedUser).apply()

                    if (user.type=="FREELANCER") {
                        val instance = Intent(this, FreelancerHomeActivity::class.java)
                        startActivity(instance)
                    } else {
                        val instance = Intent(this, ClientHomeActivity::class.java)
                        startActivity(instance)
                    }
                } else {
                    val txtError: TextView = findViewById(R.id.txtError)
                    txtError.text=message
                }
            }
        }
    }
}