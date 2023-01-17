package com.udacity.shoestore.domain

import android.content.Context
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.domain.login.LoginUseCase
import com.udacity.shoestore.domain.login.RegisterUseCase
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User

class SharedViewModel : ViewModel() {
    private val userList: MutableList<User> = mutableListOf()
    private val registerUseCase = RegisterUseCase()
    private val loginUseCase = LoginUseCase()
    private val createCardViewForShoeList = CreateCardViewForShoeList()

    fun performLogin(loginEmail: String, loginPassword: String, context: Context): Boolean {
        return loginUseCase(loginEmail, loginPassword, userList, context)
    }

    fun performRegistration(loginEmail: String, loginPassword: String, context: Context): Boolean {
        if(registerUseCase(loginEmail, loginPassword, userList, context))
            userList.add(User(loginEmail, loginPassword))
        return true
    }

    fun createCardView(shoe: Shoe, context: Context) : CardView {
        return createCardViewForShoeList(shoe, context)
    }

}