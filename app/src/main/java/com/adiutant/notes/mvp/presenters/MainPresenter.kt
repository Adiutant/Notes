package com.adiutant.notes.mvp.presenters

import android.app.Activity
//import com.adiutant.notes.NotesApplication
import com.adiutant.notes.db.NoteDao
import com.adiutant.notes.mvp.models.Notes
import com.adiutant.notes.mvp.views.MainView

import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

//import com.arellomobile.mvp.InjectViewState
//import com.arellomobile.mvp.MvpPresenter
class MainPresenter {

    @Inject lateinit var mNoteDao: NoteDao

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
    //e2
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