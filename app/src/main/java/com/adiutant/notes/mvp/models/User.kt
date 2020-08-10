package com.adiutant.notes.mvp.models

import android.graphics.Color
import com.orm.SugarRecord
import java.util.*

class User:SugarRecord {
    var styleColor:Int? = null

    constructor(color: Int) {
        this.styleColor = color
    }
    constructor()

}