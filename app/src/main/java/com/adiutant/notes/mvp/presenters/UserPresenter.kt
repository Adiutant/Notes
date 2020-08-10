package com.adiutant.notes.mvp.presenters

import android.graphics.Color
import com.adiutant.notes.db.UserDao
import java.lang.Exception

class UserPresenter {
    private val userDao = UserDao()

    fun loadColor(color: Int):Int?
    {
       var user =  userDao.getUserById(1)
        return if (user != null) {
            user.styleColor
        } else {
            user = userDao.createUser()
            user.styleColor = color
            user.save()
            return user.styleColor

        }
    }
    fun saveColor(color: Int)
    {
        val user = userDao.getUserById(1)
        if (user != null) {
            user.styleColor = color
            userDao.saveUser(user)
        }

    }

}