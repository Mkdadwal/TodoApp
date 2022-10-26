package com.example.drawernavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.drawernavigation.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var regex : Regex
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSubmit.setOnClickListener {
            regex = Regex("""\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6}""")
            if(android.util.Patterns.EMAIL_ADDRESS.matcher(binding.inputemail.text).matches()){
                Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Enter valid email",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
