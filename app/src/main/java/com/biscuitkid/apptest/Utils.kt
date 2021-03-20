package com.biscuitkid.apptest

import android.content.Context
import com.biscuitkid.apptest.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun Context.getJsonDataFromAsset(fileName: String): ArrayList<User> {
    var userJson = ""
    try {
        userJson = assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return arrayListOf()
    }

    val userListType = object : TypeToken<ArrayList<User>>() {}.type

    return Gson().fromJson(userJson, userListType)
}