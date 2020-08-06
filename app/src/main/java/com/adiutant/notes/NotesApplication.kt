package com.adiutant.notes

//import android.annotation.SuppressLint
//import android.app.Application
//import android.content.Context
//
//
//
//import com.adiutant.notes.mvp.models.AppDatabase
//import com.adiutant.notes.mvp.models.Notes
//import com.reactiveandroid.ReActiveAndroid
//import com.reactiveandroid.ReActiveConfig
//import com.reactiveandroid.internal.database.DatabaseConfig
//
//
//class NotesApplication : Application() {
//
//    companion object {
////        var graph: AppComponent = DaggerAppComponent.create()
//        @SuppressLint("StaticFieldLeak")
//        lateinit var context: Context
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        context = this
////        graph = DaggerAppComponent.builder().noteDaoModule(NoteDaoModule()).build()
//        val appDatabaseConfig = DatabaseConfig.Builder(AppDatabase::class.java)
//            .addModelClasses(Notes::class.java)
//            .build()
//
//        ReActiveAndroid.init(
//            ReActiveConfig.Builder(this)
//            .addDatabaseConfigs(appDatabaseConfig)
//            .build())
//
//    }
//
//}