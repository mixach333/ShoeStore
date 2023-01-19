package com.udacity.shoestore.domain

import android.content.Context
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
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
    private val defaultShoeList: List<Shoe> = generateListOfShoesUseCase()

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList : LiveData<List<Shoe>> get() = _shoeList

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn : LiveData<Boolean> get() = _isUserLoggedIn

    private val userList: MutableList<User> = mutableListOf(
        User("test@gmail.com", "11111aA@")
    )

    fun performLogin(user: User, context: Context): Boolean {
        return loginUseCase(user, userList, context)
    }

    fun performRegistration(loginEmail: String, loginPassword: String, context: Context): Boolean {
        if (registerUseCase(loginEmail, loginPassword, userList, context))
            userList.add(User(loginEmail, loginPassword))
        return true
    }

    fun createCardView(shoe: Shoe, context: Context): CardView {
        return createCardViewForShoeUseCase(shoe, context)
    }

    fun initializeListOfShoes(binding: FragmentShoeListingBinding) : List<CardView> {
        val cards = mutableListOf<CardView>()
        _shoeList.value?.forEach {
            cards.add(createCardView(it, binding.root.context))
        }
        return cards
    }

    fun addShoe(shoe: Shoe) : Boolean{
        if(shoe.name.isNotBlank()&& shoe.size in 1.0..60.0 &&shoe.company.isNotBlank()&&shoe.description.isNotBlank()) {
            val list = _shoeList.value?.toMutableList()
            list?.add(0, shoe)
            _shoeList.value = list
            return true
        }
        return false
    }
}