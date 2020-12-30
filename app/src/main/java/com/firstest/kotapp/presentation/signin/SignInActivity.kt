package com.firstest.kotapp.presentation.signin

import android.content.Intent
import android.os.Bundle
import com.firstest.kotapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.ext.android.inject
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.firstest.kotapp.presentation.login.LoginActivity
import com.firstest.kotapp.presentation.login.SignInError
import com.firstest.kotapp.presentation.login.SignInSuccess
import kotlinx.android.synthetic.main.activity_login.login_edit
import kotlinx.android.synthetic.main.activity_login.password_edit
import kotlinx.android.synthetic.main.activity_signin.*

class SignInActivity : AppCompatActivity(){

    private val SignInViewModel : SignInViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        SignInViewModel.signInLiveData.observe(this, Observer {
            when(it){
                is SignInSuccess -> {
                    startLoginActivity()
                }
                SignInError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("ERROR")
                        .setMessage("PLease fill the fields")
                        .setPositiveButton("OK"){
                                dialog, which ->  dialog.dismiss()
                        }
                        .show()
                }
            }
        })
        signin_button.setOnClickListener{
            SignInViewModel.onClickSignIn(login_edit.text.toString().trim(), password_edit.text.toString())
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        this.startActivity(intent)
    }
}