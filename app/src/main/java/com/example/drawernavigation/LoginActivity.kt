package com.example.drawernavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var login : Button
    lateinit var regex : Regex
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.btnLogin)
        login.setOnClickListener {
            regex = Regex("""\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6}""")
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(username.text).matches()){
                Toast.makeText(this,"Login Successfull", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Invalid Email", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
