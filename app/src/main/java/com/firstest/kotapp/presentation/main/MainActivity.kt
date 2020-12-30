package com.firstest.kotapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.firstest.kotapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.loginLiveData.observe(this, Observer {
            when(it){
                is LoginSuccess -> {

                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                    .setTitle("ERROR")
                    .setMessage("Account Not Found")
                    .setPositiveButton("OK"){
                        dialog, which ->  dialog.dismiss()
                    }
                    .show()
                }
            }
        })
        login_button.setOnClickListener{
            mainViewModel.onClickLogin(login_edit.text.toString().trim(), password_edit.text.toString())
        }
    }
}