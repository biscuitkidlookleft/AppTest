package com.biscuitkid.apptest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.biscuitkid.apptest.model.User

class UserViewModel: ViewModel() {

    val userListLiveData = MutableLiveData<ArrayList<User>>()

    private val userList = arrayListOf<User>()

    fun setUserList(list: ArrayList<User>) {
        userList.clear()
        userList.addAll(list)
        userListLiveData.postValue(userList)
    }
}