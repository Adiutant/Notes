package com.adiutant.notes.mvp.models

class User {
    var listNotes = mutableListOf<Notes>()

    fun loadNotesDB(notes:List<Notes>):List<Notes>
    {
       listNotes.addAll(notes)
        return listNotes
    }

}