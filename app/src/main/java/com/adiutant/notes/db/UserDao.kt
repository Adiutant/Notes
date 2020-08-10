package com.adiutant.notes.db;

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import com.adiutant.notes.mvp.models.User;
import com.orm.SugarRecord.*
import java.util.*

class UserDao {
    fun createUser(): User{
        val user = User()
        user.save()
        return user
    }


    /**
     * Сохраняет заметку в БД
     */
    fun saveUser(user: User):Long = user.save()

    /**
     * Загружает все существующие заметки и передает во View
     */
    fun loadAllUsers():MutableList<User> = listAll(User::class.java)

    /**
     * Ищет заметку по id и возвращает ее
     */
    fun getUserById(noteId: Long): User? = findById(User::class.java, noteId)


    /**
     * Удаляет все существующие заметки
     */
    fun deleteAllNotes() {
        deleteAll(User::class.java)

    }

    /**
     * Удаляет заметку по id
     */
    fun deleteNote(user: User) {

        user.delete()
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

