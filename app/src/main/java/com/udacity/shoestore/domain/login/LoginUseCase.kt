package com.udacity.shoestore.domain.login

import android.content.Context
import android.widget.Toast
import com.udacity.shoestore.models.User

class LoginUseCase {
    operator fun invoke(user: User, userList: List<User>, context: Context): Boolean {
        return if (userList.contains(user)) {
            true
        } else {
            Toast.makeText(context, "Incorrect email or password", Toast.LENGTH_SHORT).show()
            false
        }
    }
}