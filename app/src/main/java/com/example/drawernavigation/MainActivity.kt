package com.example.drawernavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    var todo = ArrayList<Todo>()
    lateinit var drawerLayout : DrawerLayout
    lateinit var navigationView : NavigationView
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var btnGet : Button
    lateinit var lstView2 : ListView
    //For listView

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //for navigation
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        //for menu
        when(item.itemId){
            R.id.add -> {
                var intent = Intent(this@MainActivity,AddTodoActivity::class.java)
                startActivity(intent)
                //Toast.makeText(applicationContext,"Login",Toast.LENGTH_SHORT).show()
            }
            R.id.login -> {
                var intent = Intent(this@MainActivity,LoginActivity::class.java)
                startActivity(intent)
                //Toast.makeText(applicationContext,"Login",Toast.LENGTH_SHORT).show()
            }
            R.id.register -> {
                var intent = Intent(this@MainActivity,RegisterActivity::class.java)
                startActivity(intent)
                //Toast.makeText(applicationContext,"Login",Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Button click GetRecord

//        btnGet.setOnClickListener {
//            title = edtTitle.text.toString()
//            myTask = edtTask.text.toString()
//
//            todo.add(Todo(title, myTask))
//            edtTitle.setText("")
//            edtTask.setText("")
//            listView.adapter = TodoAdapter(this@MainActivity, R.layout.todo_row, todo)
//        }
        //Navigation View
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        toggle = ActionBarDrawerToggle(this@MainActivity,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // to activate the menu item of the navigation view
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.login -> {
                    var intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(this@MainActivity,"Login",Toast.LENGTH_SHORT).show()
                }
                R.id.register -> {
                    var intent = Intent(this@MainActivity, RegisterActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(this@MainActivity,"Register",Toast.LENGTH_SHORT).show()
                }
                R.id.add -> {
                    var intent = Intent(this@MainActivity, AddTodoActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(this@MainActivity,"Profile",Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }
}