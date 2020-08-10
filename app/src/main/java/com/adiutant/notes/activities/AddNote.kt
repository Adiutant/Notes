package com.adiutant.notes.activities

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.adiutant.notes.R
import com.adiutant.notes.db.NoteDao
import com.adiutant.notes.mvp.models.Notes
import com.adiutant.notes.mvp.presenters.MainPresenter
import com.adiutant.notes.mvp.presenters.NotePresenter
import com.adiutant.notes.mvp.presenters.UserPresenter
import com.adiutant.notes.mvp.views.NoteView
import com.adiutant.notes.mvp.views.UserView
import kotlinx.android.synthetic.main.activity_add_note.*
import java.lang.Exception
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class AddNote : AppCompatActivity(),NoteView,UserView {
    //private lateinit var _backBtn:Button
    private lateinit var _back:Intent
    private lateinit var userPresenter: UserPresenter
    private lateinit var _noteShown:Notes
    private lateinit var mainText: EditText
    private lateinit var dateView:TextView
    private lateinit var toolbar: Toolbar
    private val MINUTE_VLAUE:Long = 60000
    private val DATE_NOW_MESSAGE_CODE = "Только что"
    private var isCreate = false
    private  var noteId:Long = 0

    private lateinit var presenter: NotePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        setSupportActionBar(findViewById(R.id.toolbar_add_note))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        dateView = findViewById(R.id.dateView)
        presenter = NotePresenter()
        userPresenter = UserPresenter()
         mainText = findViewById(R.id.mainText)
        onColorLoaded()
        _back = Intent(this, MainActivity::class.java)
        val bundle = intent.extras
        noteId = bundle!!.getLong("note_id")
        isCreate= bundle!!.getBoolean("is_create")
        if (!isCreate) {
            showNote(presenter.showNote(noteId))
        }
        else
        {
            showNote(presenter.showNewNote())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_note,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.newNote -> {
            // User chose the "Print" item
            try {
                val header= mainText.text.split(" ",limit =2)
                presenter.saveNote(header[0], mainText.text.toString(), _noteShown)
                //isSaved = true
            }
            catch (e:Exception)
            {

            }
            true
        }
        android.R.id.home ->{
           startActivity(_back)
            finish()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
    companion object {
        const val NOTE_DELETE_ARG = "note_id"

        fun buildIntent(activity: Activity, noteId: Long) : Intent{
            val intent = Intent(activity, AddNote::class.java)
            intent.putExtra(NOTE_DELETE_ARG, noteId)
            return intent
        }
    }


    override fun showNote(note: Notes) {
        _noteShown = note
        mainText.setText(note.text)
       val formatter= SimpleDateFormat("dd/MM/yy")
        if (note.changeDate!=null) {
            if(Date().time - note.changeDate!!.time  < MINUTE_VLAUE) {
                dateView.text = DATE_NOW_MESSAGE_CODE
            }
            else {
                dateView.text = formatter.format(note.changeDate!!)
            }
        }

    }

    override fun onNoteSaved() {
        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteDeleted() {
        TODO("Not yet implemented")
    }

    override fun showNoteInfoDialog(noteInfo: String) {
        TODO("Not yet implemented")
    }

    override fun hideNoteInfoDialog() {
        TODO("Not yet implemented")
    }

    override fun showNoteDeleteDialog() {
        TODO("Not yet implemented")
    }

    override fun hideNoteDeleteDialog() {
        TODO("Not yet implemented")
    }

    override fun onColorLoaded() {
        val color = userPresenter.loadColor(R.color.colorPrimary)!!
        window.navigationBarColor = resources.getColor(color,theme)
        window.statusBarColor = resources.getColor(color,theme)
        toolbar = findViewById(R.id.toolbar_add_note)
        toolbar.setBackgroundColor(resources.getColor(color,theme))
        setSupportActionBar(findViewById(R.id.toolbar_add_note))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

//    fun onClickBack()
//    {
//        _backBtn.setOnClickListener(object :View.OnClickListener{
//            override fun onClick(p0: View?) {
//                back(view = View(this))
//            }
//        })
//    }

}