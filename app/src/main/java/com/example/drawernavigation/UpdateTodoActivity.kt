package com.example.drawernavigation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.drawernavigation.databinding.ActivityUpdateTodoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class UpdateTodoActivity : AppCompatActivity() {
    val dataBase : FirebaseDatabase = FirebaseDatabase.getInstance()
    val refTodo : DatabaseReference = dataBase.reference.child("Todo")
    private lateinit var binding: ActivityUpdateTodoBinding
    lateinit var id : String
    lateinit var title : String
    lateinit var task : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        id = intent.getStringExtra("id").toString()
        title = intent.getStringExtra("title").toString()
        task = intent.getStringExtra("task").toString()
        binding.edtUpdateTitle.setText(title).toString()
        binding.edtUpdateAction.setText(task).toString()
//        Toast.makeText(this,"Title : ${title}",Toast.LENGTH_SHORT).show()
        binding.btnUpdateSubmit.setOnClickListener {
            binding.btnUpdateSubmit.setBackgroundColor(Color.GREEN)
            title=binding.edtUpdateTitle.text.toString()
            task=binding.edtUpdateAction.text.toString()
            val userUpdate = mutableMapOf<String,Any>()
            userUpdate["id"] = id
            userUpdate["title"] = title
            userUpdate["task"] = task
            //where to go
            //whom to meet
            //what to do
            refTodo.child(id).updateChildren(userUpdate).addOnCompleteListener { mytask->
                if(mytask.isSuccessful){
                    Toast.makeText(this@UpdateTodoActivity,"Record Updated",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@UpdateTodoActivity,"Record not Updated",Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.remove.setOnClickListener {
            binding.remove.setBackgroundColor(Color.RED)
            binding.edtUpdateTitle.setText("")
            binding.edtUpdateAction.setText("")
            refTodo.child(id).removeValue().addOnCompleteListener { mytask ->
                if (mytask.isSuccessful) {
                    Toast.makeText(this@UpdateTodoActivity, "Removed Successfully", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@UpdateTodoActivity,"Failure",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
