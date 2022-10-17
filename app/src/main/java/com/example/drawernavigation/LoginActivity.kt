package com.example.drawernavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.drawernavigation.databinding.ActivityLoginBinding
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    lateinit var regex : Regex
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            regex = Regex("""\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6}""")
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(binding.username.text!!).matches()){
                Toast.makeText(this,"Login Successfull", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Invalid Email", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
