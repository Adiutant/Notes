package com.adiutant.notes.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.adiutant.notes.R
import com.adiutant.notes.adapters.ItemClickSupport
import com.adiutant.notes.adapters.RecAdapter
import com.adiutant.notes.mvp.models.Notes
import com.adiutant.notes.mvp.models.User
import com.adiutant.notes.mvp.presenters.MainPresenter
import com.adiutant.notes.mvp.presenters.UserPresenter
import com.adiutant.notes.mvp.views.MainView
import com.adiutant.notes.mvp.views.UserView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import java.lang.Exception
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity(), MainView,UserView {
    private lateinit var _listView: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var newNoteButton:FloatingActionButton
    private  var contextPosition:Int =0
    lateinit var presenter: MainPresenter
    lateinit var userPresenter: UserPresenter
    private var isCreate = false
    private val DELETE_NOTE_CODE = "Удалить"
    private val NOTE_RUS_CODE ="Заметка"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter()
        userPresenter = UserPresenter()
        _listView = findViewById(R.id.taskList)
        newNoteButton = findViewById(R.id.newNote)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        onColorLoaded()
        newNoteButton.setOnClickListener {  isCreate=true
             openNoteScreen(presenter.openNote(-1)) }
        onNotesLoaded(presenter.loadAllNotes())
        with(ItemClickSupport.addTo(_listView)) {
            setOnItemClickListener { _, position, _ -> openNoteScreen(presenter.openNote(position)) }
            setOnItemLongClickListener { _, position, _ -> contextPosition = position
                openContextMenu(_listView); true }
        }

    }

    override fun onDestroy() {
        moveTaskToBack(true)
        super.onDestroy()
        System.runFinalization()
        exitProcess(0)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.color_1_select->
        {
            userPresenter.saveColor(R.color.colorPrimary)
            onColorLoaded()
            true
        }
        R.id.color_2_select->
        {
            userPresenter.saveColor(R.color.colorRuby)
            onColorLoaded()
            true
        }
        R.id.color_3_select->
        {
            userPresenter.saveColor(R.color.colorPink)
            onColorLoaded()
            true
        }
        R.id.color_4_select->
        {
            userPresenter.saveColor(R.color.colorLime)
            onColorLoaded()
            true
        }
        android.R.id.home ->{
            finish()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }



    override fun onNotesLoaded(notes: List<Notes>) {
        _listView.adapter= RecAdapter(notes)
        registerForContextMenu(_listView)
        updateView()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu!!.setHeaderTitle(NOTE_RUS_CODE)
        menu.add(0,v!!.id,0,DELETE_NOTE_CODE)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.title ==DELETE_NOTE_CODE)
        {
            presenter.deleteNote(contextPosition+1)
            onNoteDeleted()
        }
        return true
    }
    override fun updateView() {
        _listView.adapter?.notifyDataSetChanged()
        if (_listView.adapter?.itemCount==0)
        {
            _listView.visibility= View.GONE
        }
        else
        {
            _listView.visibility = View.VISIBLE
        }

    }
    override fun onSearchResult(notes: List<Notes>) {
        TODO("Not yet implemented")
    }

    override fun onAllNotesDeleted() {
        TODO("Not yet implemented")
    }

    override fun onNoteDeleted() {
        onNotesLoaded(presenter.loadAllNotes())

    }

    override fun openNoteScreen(noteId:Long) {
       val intent=Intent(this@MainActivity,AddNote::class.java)
        val bundle= Bundle()
        bundle.putLong("note_id",noteId)
        bundle.putBoolean("is_create",isCreate)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onColorLoaded() {
        val color = userPresenter.loadColor(R.color.colorPrimary)!!
        window.navigationBarColor = resources.getColor(color,theme);
        window.statusBarColor = resources.getColor(color,theme);
        newNoteButton.backgroundTintList =
            ColorStateList.valueOf(resources.getColor(color,theme))
        newNoteButton.show()
        toolbar = findViewById(R.id.toolbar)
        toolbar.setBackgroundColor(resources.getColor(color,theme))
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}