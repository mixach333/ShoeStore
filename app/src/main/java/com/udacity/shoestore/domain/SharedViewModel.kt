package com.udacity.shoestore.domain

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.domain.login.LoginUseCase
import com.udacity.shoestore.domain.login.RegisterUseCase
import com.udacity.shoestore.models.User

class SharedViewModel : ViewModel() {
    private val userList: MutableList<User> = mutableListOf()
    private val registerUseCase = RegisterUseCase()
    private val loginUseCase = LoginUseCase()

    fun performLogin(loginEmail: String, loginPassword: String, context: Context): Boolean {
        return loginUseCase(loginEmail, loginPassword, userList, context)
    }

    fun performRegistration(loginEmail: String, loginPassword: String, context: Context): Boolean {
        if(registerUseCase(loginEmail, loginPassword, userList, context))
            userList.add(User(loginEmail, loginPassword))
        return true
    }


}