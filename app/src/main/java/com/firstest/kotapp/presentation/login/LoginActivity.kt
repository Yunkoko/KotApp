package com.firstest.kotapp.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.firstest.kotapp.R
import com.firstest.kotapp.presentation.main.MainActivity
import com.firstest.kotapp.presentation.signin.SignInActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.login_edit
import kotlinx.android.synthetic.main.activity_login.password_edit
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private val loginViewModel : LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel.loginLiveData.observe(this, Observer {
            when(it){
                is LoginSuccess -> {
                    startMainActivity()
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
            loginViewModel.onClickLogin(login_edit.text.toString().trim(), password_edit.text.toString())
        }
        register_button.setOnClickListener{
            startSignInActivity()
        }
    }

    private fun startSignInActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        this.startActivity(intent)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }
}