package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//  Bridge that tells teh recyclerView how to display the data we give it
class TaskItemAdaptor(val listOfItems: List<String>, val longClickListener: OnLongClickListener ) : RecyclerView.Adapter<TaskItemAdaptor.ViewHolder>() {

    interface OnLongClickListener {
        fun onItemLongClicked(position: Int)
    }
    // Usually involves inflating a layout from XML and return the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = listOfItems.get(position)

        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    // Provide direct reference to each of the views within data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // TODO
        // Populate data by grabbing references to the strings in our app
        // Store references
        val textView: TextView

        init {
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener {
                Log.i("Jon", "Long clicked on item: " + adapterPosition)
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }
    }
}