package com.example.drawernavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class UpdateTodoActivity : AppCompatActivity() {
    val dataBase : FirebaseDatabase = FirebaseDatabase.getInstance()
    val refTodo : DatabaseReference = dataBase.reference.child("Todo")

    lateinit var edtUpdateTitle : EditText
    lateinit var edtUpdateAction : EditText
    lateinit var update : Button
    lateinit var remove : Button
    lateinit var id : String
    lateinit var title : String
    lateinit var task : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_todo)

        edtUpdateTitle = findViewById(R.id.edtUpdateTitle)
        edtUpdateAction = findViewById(R.id.edtUpdateAction)
        update = findViewById(R.id.btnUpdateSubmit)
        remove = findViewById(R.id.remove)
        id = intent.getStringExtra("id").toString()
        title = intent.getStringExtra("title").toString()
        task = intent.getStringExtra("task").toString()
        edtUpdateTitle.setText(title).toString()
        edtUpdateAction.setText(task).toString()
//        Toast.makeText(this,"Title : ${title}",Toast.LENGTH_SHORT).show()
        update.setOnClickListener {
            title=edtUpdateTitle.text.toString()
            task=edtUpdateAction.text.toString()
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


        remove.setOnClickListener {
            edtUpdateTitle.setText("")
            edtUpdateAction.setText("")
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