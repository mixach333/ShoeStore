package com.udacity.shoestore.domain

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.User


class LoginViewModel : ViewModel() {
    private val userList: MutableList<User> = mutableListOf()
    private val validateEmailAndPasswordUseCase = ValidateEmailAndPasswordUseCase()

    fun performLogin(loginEmail: String, loginPassword: String, context: Context): Boolean {
        return if (userList.contains(User(loginEmail, loginPassword))) {
            true
        } else {
            Toast.makeText(context, "Incorrect email or password", Toast.LENGTH_SHORT).show()
            false
        }
    }

    fun performRegistration(loginEmail: String, loginPassword: String, context: Context): Boolean {
        userList.forEach { user ->
            if (user.email == loginEmail) {
                Toast.makeText(
                    context,
                    "User with such email-address already exists",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        }
        if (validateEmailAndPasswordUseCase.invoke(loginEmail, loginPassword, context)) {
            userList.add(User(loginEmail, loginPassword))
            Toast.makeText(context, "User registered! You can log in now", Toast.LENGTH_SHORT)
                .show()
            return true
        }
        return false
    }

}