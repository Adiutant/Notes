package com.adiutant.notes.mvp.views

import android.provider.ContactsContract
import com.adiutant.notes.mvp.models.Notes



interface NoteView  {
    fun doOnClick()
    fun showNote(note: Notes)

    fun onNoteSaved()

    fun onNoteDeleted()

    fun showNoteInfoDialog(noteInfo: String)

    fun hideNoteInfoDialog()

    fun showNoteDeleteDialog()

    fun hideNoteDeleteDialog()
}