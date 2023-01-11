package com.udacity.shoestore.domain.login

import android.content.Context
import android.widget.Toast
import com.udacity.shoestore.models.User

class RegisterUseCase {
    private val validateEmailAndPasswordUseCase = ValidateEmailAndPasswordUseCase()
    operator fun invoke(login: String, password: String, userList: List<User>, context: Context) : Boolean{
        userList.forEach { user ->
            if (user.email == login) {
                Toast.makeText(
                    context,
                    "User with such email-address already exists",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        }
        if (validateEmailAndPasswordUseCase.invoke(login, password, context)) {
            Toast.makeText(context, "User registered! You can log in now", Toast.LENGTH_SHORT)
                .show()
            return true
        }
        return false
    }
}