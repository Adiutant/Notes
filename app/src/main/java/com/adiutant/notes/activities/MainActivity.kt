package com.adiutant.notes.activities

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.adiutant.notes.R
import com.adiutant.notes.adapters.ItemClickSupport
import com.adiutant.notes.adapters.RecAdapter
import com.adiutant.notes.mvp.models.Notes
import com.adiutant.notes.mvp.presenters.MainPresenter
import com.adiutant.notes.mvp.views.MainView
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout


//import com.arellomobile.mvp.presenter.InjectPresenter


class MainActivity : AppCompatActivity(), MainView {
    //private lateinit var adapter: ArrayAdapter<Notes>
    private lateinit var _listView: RecyclerView
    private lateinit var _tabLayout: TabLayout
    private lateinit var _newNote: TabItem
    private  var contextPosition:Int =0
    lateinit var presenter: MainPresenter
    private var isCreate = false

    private lateinit var allNotes: ArrayList<Notes>
    //private lateinit var dbHelper: DBHelper
    //private var currentUser = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter()
        _listView = findViewById(R.id.taskList)
        _tabLayout = findViewById(R.id.tabLayout)
        onNotesLoaded(presenter.loadAllNotes())
        with(ItemClickSupport.addTo(_listView)) {
            setOnItemClickListener { _, position, _ -> openNoteScreen(presenter.openNote(position)) }
            setOnItemLongClickListener { _, position, _ -> contextPosition = position
                openContextMenu(_listView); true }
        }
        _tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                isCreate=true
                openNoteScreen(presenter.openNote(-1))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })

        //listNotes.add(Notes("Note one",Date(System.currentTimeMillis()),Date(System.currentTimeMillis())))
        //listNotes.add(Notes("Note two",Date(System.currentTimeMillis()),Date(System.currentTimeMillis())))
        //listNotes.add(Notes("Note three",Date(System.currentTimeMillis()),Date(System.currentTimeMillis())))
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
        menu!!.setHeaderTitle("Заметка")
        menu.add(0,v!!.id,0,"Удалить")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item!!.title =="Удалить")
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


//    private fun act(view: View)
//    {
//        var intent = Intent(this, AddNote::class.java)
//        startActivity(intent)
//    }

}