package com.adiutant.notes.mvp.presenters

//import com.adiutant.notes.NotesApplication
import com.adiutant.notes.db.NoteDao
import com.adiutant.notes.mvp.models.Notes
import com.adiutant.notes.mvp.views.NoteView
import org.greenrobot.eventbus.EventBus
import java.util.*
import javax.inject.Inject


class NotePresenter {


    private var mNoteDao: NoteDao = NoteDao()
    private lateinit var mNote: Notes

//    init {
////        NotesApplication.graph.inject(this)
////       EventBus.getDefault().register(this)
//    }

//    fun saveNote(title: String, text: String) {
//        mNote.title = title
//        mNote.text = text
//        mNote.changeDate = Date()
//        mNoteDao.saveNote(mNote)
//        //EventBus.getDefault().post(NoteEditAction(note.id))
//       // viewState.onNoteSaved()
//    }
    fun saveNote(title: String,text: String,note:Notes)
    {
        mNote.title=title
        mNote.text = text
        mNote.changeDate= Date()
        //mNoteDao.deleteNote(note)
        mNoteDao.saveNote(mNote)
    }
    fun showNewNote():Notes
    {
        mNote = Notes()
        return mNote
    }
    fun showNote(noteId: Long):Notes {
        var notes = mNoteDao.loadAllNotes()
        mNote = notes[noteId.toInt()]
        return mNote
    //    viewState.showNote(mNote)
    }
    fun deleteNote(id:Long)
    {
      //  mNoteDao=NoteDao()
       val note =  mNoteDao.getNoteById(id)
        if (note != null) {
            mNoteDao.deleteNote(note)
        }
    }

}