package com.monitech.favore_app.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.monitech.favore_app.R
import com.monitech.favore_app.dto.UserLoginDTO
import com.monitech.favore_app.services.LoginService

class SignInClientActivity : AppCompatActivity() {

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_client)

        val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
        val storedUser = Gson().toJson(null)

        val loginService = LoginService()

        val btnFreelancer: Button = findViewById(R.id.btnFreelancer)
        btnFreelancer.setOnClickListener(){
            val instance = Intent(this, SignInFreelancerActivity::class.java)
            startActivity(instance)
        }

        val btnReturn: ImageButton = findViewById(R.id.btnReturn)
        btnReturn.setOnClickListener(){
            finish()
        }

        val btnSignUp: Button = findViewById(R.id.btnSignUp)
        btnSignUp.setOnClickListener(){
            val instance = Intent(this, SignUpActivity::class.java)
            startActivity(instance)
        }

        val emailField: TextInputLayout = findViewById(R.id.emailTextField)
        emailField.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                emailField.error = null
            }

            override fun afterTextChanged(s: Editable) {}
        })

        val btnSignIn: Button = findViewById(R.id.btnSignIn)
        btnSignIn.setOnClickListener(){

            //get the value of the text field inputs

            val passwordField: TextInputLayout = findViewById(R.id.passwordTextField)
            val email = emailField.editText?.text.toString()

            if (!isValidEmail(email)) {
                emailField.error = "Invalid email format"
                return@setOnClickListener
            }

            val userLoginDTO = UserLoginDTO(
                emailField.editText?.text.toString(),
                passwordField.editText?.text.toString()
            )

            loginService.login(userLoginDTO) { user, message ->
                if (user != null) {
                    if (user.type!="CLIENT") {
                        val txtError: TextView = findViewById(R.id.txtError)
                        txtError.text="You are not a client"
                    } else {
                        val instance = Intent(this, ClientHomeActivity::class.java)

                        // Save user data to shared preferences
                        val storedUser = Gson().toJson(user)
                        val sharedPreferences = getSharedPreferences("favore", Context.MODE_PRIVATE)
                        sharedPreferences.edit().putString("user", storedUser).apply()

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