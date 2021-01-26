package com.rahul.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INoteRVAdapter {
    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter =NotesRVAdapter(this,this)
        recyclerview.adapter =adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allnotes.observe(this, Observer {list->
            list?.let {
                adapter.updatelist(it)
            }

        })


    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text}Deleted",Toast.LENGTH_SHORT).show()

    }

    fun submit(view: View) {
        val notetext = input.text.toString()
        if (notetext.isNotEmpty()){
            viewModel.insertnote(Note(notetext))
            Toast.makeText(this,"$notetext Inserted",Toast.LENGTH_SHORT).show()

        }
    }
}