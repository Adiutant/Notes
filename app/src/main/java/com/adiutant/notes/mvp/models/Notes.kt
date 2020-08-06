package com.adiutant.notes.mvp.models



import com.orm.SugarApp
import com.orm.SugarRecord
import com.orm.dsl.Table
import java.util.*



class Notes:SugarRecord {

    //var id:Long = 0

    var title: String? = null

    var text: String? = null

    var changeDate: Date? = null

    constructor(title: String, changeDate: Date) {
        this.title = title
        this.changeDate = changeDate
    }

    constructor()

//    fun getInfo(): String = "Название:\n$title\n" +
//            "Время создания:\n${formatDate(createDate)}\n" +
//            "Время изменения:\n${formatDate(changeDate)}";
}
