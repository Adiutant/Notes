package com.adiutant.notes.mvp.views

import com.adiutant.notes.mvp.models.Notes

//import com.arellomobile.mvp.MvpView
//import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
//import com.arellomobile.mvp.viewstate.strategy.StateStrategyType


interface MainView{

    fun onNotesLoaded(notes: List<Notes>)

    fun updateView()

    fun onSearchResult(notes: List<Notes>)

    fun onAllNotesDeleted()

    fun onNoteDeleted()
    fun openNoteScreen(noteId:Long)

//    fun showNoteInfoDialog(noteInfo: String)
//
//    fun hideNoteInfoDialog()
//
//    fun showNoteDeleteDialog(notePosition: Int)
//
//    fun hideNoteDeleteDialog()
//
//    fun showNoteContextDialog(notePosition: Int)
//
//    fun hideNoteContextDialog()

}