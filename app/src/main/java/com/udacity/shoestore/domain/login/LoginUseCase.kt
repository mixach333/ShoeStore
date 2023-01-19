package com.udacity.shoestore.domain.login

import android.content.Context
import android.widget.Toast
import com.udacity.shoestore.models.User

class LoginUseCase {
    operator fun invoke(inputEmail: String, inputPassword: String, userList: List<User>, context: Context): Pair<User?, Boolean> {
        userList.forEach() {
            if(it.email==inputEmail&&it.password==inputPassword) return Pair(it, true)
        }
            Toast.makeText(context, "Incorrect email or password", Toast.LENGTH_SHORT).show()
            return Pair(null, false)
        }
    }
