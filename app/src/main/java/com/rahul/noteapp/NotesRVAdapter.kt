package com.rahul.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter(private val context: Context, private val listner:INoteRVAdapter) : RecyclerView.Adapter<NotesRVAdapter.Noteviewholder>() {
    val allnotes = ArrayList<Note>()

    inner class Noteviewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deletebutton = itemView.findViewById<ImageView>(R.id.deletebutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Noteviewholder {
      val viewHolder=Noteviewholder(  LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deletebutton.setOnClickListener{
            listner.onItemClicked(allnotes[viewHolder.adapterPosition])

        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: Noteviewholder, position: Int) {
        val currentNote = allnotes[position]
        holder.textView.text = currentNote.text
    }

    override fun getItemCount(): Int {
      return  allnotes.size
    }
    fun updatelist(newlist : List<Note>){
        allnotes.clear()
        allnotes.addAll(newlist)
        notifyDataSetChanged()

    }
}
interface INoteRVAdapter{
    fun onItemClicked(note:Note)
}