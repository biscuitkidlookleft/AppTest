package com.biscuitkid.apptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.biscuitkid.apptest.adapter.UserAdapter
import com.biscuitkid.apptest.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm by viewModel<UserViewModel>()
    private val userAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindObserver()

        val viewManager = LinearLayoutManager(this)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = userAdapter
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )

        }

        getData()
    }

    private fun getData() {
        vm.setUserList(getJsonDataFromAsset("data.json"))
    }

    private fun bindObserver() {
        vm.userListLiveData.observe(this, Observer {
            userAdapter.addAll(it)
            pullToRefresh.isRefreshing = false
        })
    }
}