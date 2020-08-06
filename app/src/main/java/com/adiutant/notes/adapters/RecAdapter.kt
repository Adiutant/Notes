package com.adiutant.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adiutant.notes.R
import com.adiutant.notes.mvp.models.Notes
import com.adiutant.notes.mvp.presenters.MainPresenter


class RecAdapter: RecyclerView.Adapter<RecAdapter.ViewHolder> {

    private var mNotesList: List<Notes> = ArrayList()
    private var  mainPresenter: MainPresenter = MainPresenter()

constructor(notesList:List<Notes>)
{
    mNotesList=notesList
}
    class ViewHolder:RecyclerView.ViewHolder
    {
        var mNoteHeader:TextView
        constructor(itemView:View):super(itemView)
        {
            mNoteHeader=itemView.findViewById(R.id.header)
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return mNotesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note=mNotesList[position]
        holder.mNoteHeader.text=note.title
        holder.itemView.setOnClickListener{mainPresenter.openNote(position)}


    }
}