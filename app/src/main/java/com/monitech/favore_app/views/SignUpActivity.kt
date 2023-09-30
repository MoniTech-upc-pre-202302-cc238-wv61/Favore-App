package com.monitech.favore_app.views

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.monitech.favore_app.R
import com.monitech.favore_app.dto.UserCreateDTO
import com.monitech.favore_app.services.UserService

class SignUpActivity : AppCompatActivity() {

    private val btnSignUp: Button = findViewById(R.id.btnSignUp)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

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

        btnSignUp.setOnClickListener(){
            val userCreateDTO = UserCreateDTO(
                firstNameTextField.editText?.text.toString(),
                lastNameTextField.editText?.text.toString(),
                emailTextField.editText?.text.toString(),
                passwordTextField.editText?.text.toString(),
                "",
                true,
                selected
            )

            userService.createUser(userCreateDTO) { user, message ->
                if (user != null) {
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