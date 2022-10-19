package com.example.drawernavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.drawernavigation.databinding.ActivityLoginBinding
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
//Binding-Add
    private lateinit var binding: ActivityLoginBinding

    lateinit var regex : Regex
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var login : Button
    lateinit var register : Button
    lateinit var regex : Regex
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        login = findViewById(R.id.btnLogin)
        register = findViewById(R.id.btnRegister)
        login.setOnClickListener {
//master
            regex = Regex("""\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6}""")
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(binding.username.text!!).matches()){
                Toast.makeText(this,"Login Successfull", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Invalid Email", Toast.LENGTH_SHORT).show()
            }
        }
        register.setOnClickListener {
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
