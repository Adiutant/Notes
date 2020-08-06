package com.adiutant.notes.db


import com.adiutant.notes.mvp.models.Notes
import com.orm.SugarRecord.*
import java.util.*


class NoteDao {
    fun createNote(): Notes {
        val note = Notes("Новая заметка", Date(System.currentTimeMillis()))
        note.save()
        return note
    }


    /**
     * Сохраняет заметку в БД
     */
    fun saveNote(note: Notes):Long = note.save()

    /**
     * Загружает все существующие заметки и передает во View
     */
    fun loadAllNotes():MutableList<Notes> = listAll(Notes::class.java)

    /**
     * Ищет заметку по id и возвращает ее
     */
    fun getNoteById(noteId: Long): Notes? = findById(Notes::class.java, noteId)


    /**
     * Удаляет все существующие заметки
     */
    fun deleteAllNotes() {
        deleteAll(Notes::class.java)

    }

    /**
     * Удаляет заметку по id
     */
    fun deleteNote(note: Notes) {

        note.delete()
//        var i=note.id
//         var noteBuffer:Notes? = null
//        while (noteBuffer!=null)
//        {
//            i++
//            noteBuffer = getNoteById(i)
//            if (noteBuffer != null) {
//                noteBuffer.delete()
//                noteBuffer.id = noteBuffer.id-1
//                saveNote(noteBuffer)
//            }
//
//        }
    }

}
