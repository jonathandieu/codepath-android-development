package com.example.simpletodo

import android.os.Bundle
import android.os.FileUtils
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
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodo.databinding.ActivityMainBinding
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    var listOfTasks = mutableListOf<String>()
    lateinit var adapter : TaskItemAdaptor
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdaptor.OnLongClickListener {
            override fun onItemLongClicked(position: Int) {
                // 1. Remove the item from the list
                listOfTasks.removeAt(position)
                // 2. Notify the adapter that something has changed in our data set
                adapter.notifyDataSetChanged()

                saveItems()
            }

        }
        // 1. Detect when the user clicks on the add button
//        findViewById<Button>(R.id.button).setOnClickListener {
//            // Any code written here will be executed when user clicks on a button
//            Log.i("Jon", "User clicked on button")
//        }
//        listOfTasks.add("Do laundry")
//        listOfTasks.add("Go for walk")

        loadItems()

        // Look up recyclerView in layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // Create adapter passing in the sample user data
        adapter = TaskItemAdaptor(listOfTasks, onLongClickListener )
        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the button and input field so that the user can enter a task and add it to the list
        val inputTextField = findViewById<EditText>(R.id.addTaskField)

        // Get a reference to the button and set an onclickListener
        findViewById<Button>(R.id.button).setOnClickListener {
            // 1. Grab the text the user has inputted
            val userInputtedTask = inputTextField.text.toString()

            // 2. Add the string to our list of tasks: listofTasks
            listOfTasks.add(userInputtedTask)

            // Notify the adapter that the data has been updated
            adapter.notifyItemInserted(listOfTasks.size - 1)

            // 3. Reset the text field so that it autoclears on every tasks
            inputTextField.setText("")

            saveItems()
        }

    }

    // Save the data that the user has inputted
    // Save the data by reading writing to file

    // Get the file we need
    fun getDataFile() : File {
        // Every line is going to represent a specific task in our list of tasks
        return File(filesDir, "data.txt")
    }

    // Load the items by reading every line in the file
    fun loadItems() {
        try {
        listOfTasks = org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    // Save items by writing into data file
    fun saveItems() {
        try {
        org.apache.commons.io.FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }
}