package com.udacity.shoestore.domain.login

import android.content.Context
import android.widget.Toast
import com.udacity.shoestore.models.User

class LoginUseCase {
    operator fun invoke(login: String, password: String, userList: List<User>, context: Context) : Boolean{
        return if (userList.contains(User(login, password))) {
            true
        } else {
            Toast.makeText(context, "Incorrect email or password", Toast.LENGTH_SHORT).show()
            false
        }
    }
}