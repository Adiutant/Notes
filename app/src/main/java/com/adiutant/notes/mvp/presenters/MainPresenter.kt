package com.adiutant.notes.mvp.presenters

import android.app.Activity
import com.adiutant.notes.db.NoteDao
import com.adiutant.notes.mvp.models.Notes


class MainPresenter {

    private var mNoteDao = NoteDao()

    private lateinit var notesList: MutableList<Notes>

    init {
//        EventBus.getDefault().register(this)
    }
    fun openNote(position:Int):Long
    {
        return position.toLong()
    }
    fun openNewNote(activity: Activity):Long {
        val newNote = mNoteDao.createNote()
        notesList.add(newNote)
        return openNote(notesList.indexOf(newNote)+1)
    }
    fun loadAllNotes(): MutableList<Notes> {
        mNoteDao = NoteDao()
        notesList = mNoteDao.loadAllNotes()
        return notesList
    }
    //e
    fun deleteNote(position:Int)
    {
        var note = notesList[position-1]
        if (note!=null)
        {
            notesList.removeAt(position-1)
            mNoteDao.deleteNote(note)
        }


    }
}