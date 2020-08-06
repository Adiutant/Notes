package com.adiutant.notes.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.adiutant.notes.mvp.models.Notes

import java.util.*


class DBHelper(context: Context?): SQLiteOpenHelper(context,"data", null, 1){

    fun DBHelper() {
        // конструктор суперкласса
        //super.(context, "myDB", null, 1);
    }
    @SuppressLint("SdCardPath")
    private val DB_PATH = "/data/data/com.adiutant.notes/databases/"
    private val DB_NAME = "data.db"
    private var myDataBase: SQLiteDatabase? = null

    override fun onCreate(db: SQLiteDatabase) {
        //Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями

    }
    fun getDb():SQLiteDatabase?
    {
        return myDataBase;
    }
    fun getData():List<Notes>
    {
        var listNotes = mutableListOf<Notes>()
        var cv = ContentValues()
        var c: Cursor? = myDataBase?.query("notes", null, null, null, null, null, null)
        if (c?.moveToFirst()!!) {

            // определяем номера столбцов по имени в выборке
            var idColIndex = c.getColumnIndex("id")
            var timeColIndex = c.getColumnIndex("time_changed")
            var headerColIndex = c.getColumnIndex("header")
            var bodyColIndex = c.getColumnIndex("body")

            do {
                // adapterfeed
                listNotes.add(Notes(c.getString(headerColIndex), Date(c.getLong(timeColIndex))))
            } while (c.moveToNext());
        }
        c.close()
        return listNotes
    }

    fun createDataBase()
    {
        try {


       // if (!checkDataBase()) {
            myDataBase = this.writableDatabase
            myDataBase?.execSQL(
                "create table notes ("
                        + "id integer primary key autoincrement,"
                        + "time_changed integer,"
                        + "header text,"
                        + "body text" + ");"
            );
        //}
        }
        catch (e:Exception) {
            myDataBase = this.writableDatabase
           // openDataBase()
        }


    }

    private fun checkDataBase(): Boolean {
        var checkDB: SQLiteDatabase? = null
        try {
            val myPath = DB_PATH + DB_NAME
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)
        } catch (e: SQLiteException) {
            //база еще не существует
        }
        checkDB?.close()
        return checkDB != null
    }

//    @Throws(SQLException::class)
//    fun openDataBase() {
//        //открываем БД
//        val myPath = DB_PATH + DB_NAME
//        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)
//    }

    @Synchronized
    override fun close() {
        if (myDataBase != null) myDataBase!!.close()
        super.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion:Int, newVersion:Int) {

    }
}

