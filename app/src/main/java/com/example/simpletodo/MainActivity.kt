package com.example.simpletodo

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val listOfTasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Detect when the user clicks on the add button
        findViewById<Button>(R.id.button).setOnClickListener {
            // Any code written here will be executed when user clicks on a button
            Log.i("Jon", "User clicked on button")
        }
        listOfTasks.add("Do laundry")
        listOfTasks.add("Go for walk")

        // Look up recyclerView in layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // Create adapter passing in the sample user data
        val adapter = TaskItemAdaptor(listOfTasks)
        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



    }
}