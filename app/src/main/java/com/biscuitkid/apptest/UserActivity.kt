package com.biscuitkid.apptest

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import com.biscuitkid.apptest.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserActivity: AppCompatActivity() {

    private val vm by viewModel<UserViewModel>()

    companion object {
        fun launch(context: Context, position: Int) {
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        bindObserver()
        clickListener()

        val positon = intent.getIntExtra("position", 0)
        vm.getUser(positon)

    }

    private fun clickListener() {
        btnSave.setOnClickListener {
            hideKeyboard()
            vm.saveUser(editTextFirstName.text.toString(),
                editTextLastName.text.toString(),
                editTextEmail.text.toString(),
                editTextPhone.text.toString())

            Toast.makeText(this@UserActivity, "Saved", Toast.LENGTH_LONG).show()
        }

    }

    private fun bindObserver() {
        vm.userLiveData.observe(this, Observer {
            editTextFirstName.setText(it.firstName)
            editTextLastName.setText(it.lastName)
            editTextEmail.setText(it.email)
            editTextPhone.setText(it.phone)
        })

    }

    private fun hideKeyboard() {
        val imm: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        var view: View? = currentFocus

        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}