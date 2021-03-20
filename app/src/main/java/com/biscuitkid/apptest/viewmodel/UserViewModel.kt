package com.biscuitkid.apptest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.biscuitkid.apptest.model.User

class UserViewModel: ViewModel() {

    val userLiveData = MutableLiveData<User>()
    val userListLiveData = MutableLiveData<ArrayList<User>>()

    private val userList = arrayListOf<User>()
    private var position = -2

    fun setUserList(list: ArrayList<User>) {
        userList.clear()
        userList.addAll(list)
        userListLiveData.postValue(userList)
    }

    fun getUser(pos: Int) {
        position = pos
        userLiveData.postValue(userList[position])
    }

    fun saveUser(firstName: String, lastName: String, phone: String, email: String) {
        userList[position].firstName = firstName
        userList[position].lastName = lastName
        userList[position].phone = phone
        userList[position].email = email
    }
}