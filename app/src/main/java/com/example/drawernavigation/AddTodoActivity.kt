package com.example.drawernavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*

class AddTodoActivity : AppCompatActivity() {
    //FireBase Declaration
    val dataBase : FirebaseDatabase = FirebaseDatabase.getInstance()/*this function will consult  with
    google.service.json file and from that we will get the path where database is located*/
    val refTodo : DatabaseReference = dataBase.reference.child("Todo") //creates a table


    //Navigation

    var arrTodo = ArrayList<TodoModel>()
    lateinit var drawerLayout : DrawerLayout
    lateinit var navigationView : NavigationView
    lateinit var toggle : ActionBarDrawerToggle
    //For listView
    lateinit var adapter : ArrayAdapter<TodoAdapter>
    lateinit var edtTitle : EditText
    lateinit var edtTask : EditText
    lateinit var btnSubmit : Button
    lateinit var listView : ListView
    lateinit var title : String
    lateinit var task : String
    lateinit var btnGet : Button

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        when(item.itemId){
            R.id.login -> {
                var intent = Intent(this@AddTodoActivity,LoginActivity::class.java)
                startActivity(intent)
                //Toast.makeText(applicationContext,"Login",Toast.LENGTH_SHORT).show()
            }
            R.id.add -> {
                var intent = Intent(this@AddTodoActivity,MainActivity::class.java)
                startActivity(intent)
                //Toast.makeText(applicationContext,"Profile", Toast.LENGTH_SHORT).show()
            }
            R.id.register -> {
                var intent = Intent(this@AddTodoActivity,RegisterActivity::class.java)
                startActivity(intent)
                //Toast.makeText(applicationContext,"Register",Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //ListView Initialization
        edtTitle = findViewById(R.id.edtTitle)
        edtTask = findViewById(R.id.edtTask)
        btnSubmit = findViewById(R.id.btnSubmit)
        listView = findViewById(R.id.listView)
        btnGet = findViewById(R.id.btnGet)

        //Button Click to Save Record

        btnSubmit.setOnClickListener {
            title = edtTitle.text.toString()
            task = edtTask.text.toString()

            //--------Insert data in Firebase--------
            val token : String = refTodo.push().key.toString()
            val todo1 = TodoModel(token,title,task)
            //-----------Insertion was in context to individual record/child----------
            refTodo.child(token).setValue(todo1).addOnCompleteListener {mytask ->
                if(mytask.isSuccessful){
                    Toast.makeText(this,"Record inserted successfully",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Record Failed",Toast.LENGTH_SHORT).show()
                }
            }

            //--------------------------
            //Toast.makeText(this,title,Toast.LENGTH_SHORT).show()
            edtTitle.setText("")
            edtTask.setText("")
            //listView.adapter = TodoAdapter(this@AddTodoActivity, R.layout.todo_row, arrTodo)
        }
        //----Get Record usinf this button----
        btnGet.setOnClickListener {
            //addValueEventListener : It will be called whenever there is a change in the collection
            //ValueEventListener : It is an Interface that provides the abstract method of ehat to
            // be done wiith the instance of DataSnapShot


            refTodo.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    arrTodo.clear()
                    for (eachTodo in snapshot.children){
                        val todo = eachTodo.getValue(TodoModel::class.java)
                        if(todo != null){
                            println("Id : ${todo.id}")
                            println("Title : ${todo.title}")
                            println("Task : ${todo.task}")
                            println("--------------")
                            arrTodo.add(todo)       //Array of Object
                        }
                    }
                    //Array List is filled
                    listView.adapter = TodoAdapter(this@AddTodoActivity, R.layout.todo_row, arrTodo)
                }

                override fun onCancelled(error: DatabaseError) {}

            })
        }
        //Navigation View
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        toggle = ActionBarDrawerToggle(this@AddTodoActivity,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // to activate the menu item of the navigation view
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.login -> {
                    var intent = Intent(this@AddTodoActivity, LoginActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(this@MainActivity,"Login",Toast.LENGTH_SHORT).show()
                }
                R.id.register -> {
                    var intent = Intent(this@AddTodoActivity, RegisterActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(this@MainActivity,"Register",Toast.LENGTH_SHORT).show()
                }
                R.id.add -> {
                    var intent = Intent(this@AddTodoActivity, MainActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(this@AddTodoActivity,"Profile",Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@AddTodoActivity,UpdateTodoActivity::class.java)
            //intent.putExtra("id",arrTodo[position].title)
            intent.putExtra("id",arrTodo[position].id).toString()
            intent.putExtra("title",arrTodo[position].title).toString()
            intent.putExtra("task",arrTodo[position].task).toString()
            startActivity(intent)
            //Toast.makeText(this@AddTodoActivity,"Title : ${arrTodo[position].id}\nTask : ${arrTodo[position].task}",Toast.LENGTH_SHORT).show()
        }
    }
}