package com.example.myapplication

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val items = mutableListOf<Int>()
    private val adapter = CustomRecyclerViewAdapter(items)
    private val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
    private val fab : FloatingActionButton = findViewById(R.id.fab)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter = adapter
        adjustGridColumns()


        fab.setOnClickListener {
            items.add(items.size + 1)
            adapter.notifyItemInserted(items.size - 1)
            }

    }
   private fun adjustGridColumns(){
        val columns = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 4
        recyclerView.layoutManager = GridLayoutManager(this, columns)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        adjustGridColumns()
    }
}

class CustomRecyclerViewAdapter(private val items : List<Int>) : RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
        fun bind(number: Int) {
            itemView.findViewById<TextView>(R.id.numberItem).text = number.toString()
            itemView.setBackgroundColor(
                if (number % 2 == 0) itemView.context.getColor(R.color.red)
                else itemView.context.getColor(R.color.blue))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {

        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

}