package com.udacity.shoestore.domain

import android.content.Context
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.domain.login.LoginUseCase
import com.udacity.shoestore.domain.login.RegisterUseCase
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.User
import kotlinx.android.synthetic.main.fragment_shoe_listing.view.*

class SharedViewModel : ViewModel() {
    private val registerUseCase = RegisterUseCase()
    private val loginUseCase = LoginUseCase()
    private val createCardViewForShoeUseCase = CreateCardViewForShoeUseCase()
    private val generateListOfShoesUseCase = GenerateListOfShoesUseCase()

    private val shoeList: MutableList<Shoe> = generateListOfShoesUseCase()
    private val userList: MutableList<User> = mutableListOf(
        User("test@gmail.com", "11111aA@", shoeList)
    )

    fun performLogin(user: User, context: Context): Boolean {
        return loginUseCase(user, userList, context)
    }

    fun performRegistration(loginEmail: String, loginPassword: String, context: Context): Boolean {
        if (registerUseCase(loginEmail, loginPassword, userList, context))
            userList.add(User(loginEmail, loginPassword, shoeList))
        return true
    }

    fun createCardView(shoe: Shoe, context: Context): CardView {
        return createCardViewForShoeUseCase(shoe, context)
    }

    fun initializeDefaultListOfShoes(binding: FragmentShoeListingBinding) {
        shoeList.forEach {
            val view = createCardView(it, binding.root.context)
            binding.root.linear_layout.addView(view)
        }
    }

    fun getListOfShoes() = shoeList
}