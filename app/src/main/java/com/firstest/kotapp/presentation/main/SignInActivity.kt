package com.firstest.kotapp.presentation.main

import android.os.Bundle
import com.firstest.kotapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class SignInActivity : AppCompatActivity(){

    private val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_page)

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
    }
}