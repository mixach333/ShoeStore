package com.udacity.shoestore.domain

//TODO remove unused import/imports
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

    private val _currentUser = MutableLiveData<User>()
    //TODO according to the logic currentUser should be used for read access, but the property
    // is never used
    val currentUser: LiveData<User> get() = _currentUser

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>> get() = _shoeList

    private val _isUserLoggedIn = MutableLiveData(false)
    val isUserLoggedIn: LiveData<Boolean> get() = _isUserLoggedIn

    private val userList: MutableList<User> = mutableListOf(
        User("test@gmail.com", "11111aA@", defaultShoeList)
    )

    init {
        _shoeList.value = defaultShoeList
    }

    fun performLogin(inputEmail: String, inputPassword: String, context: Context): Boolean {
        val validationPair = loginUseCase(inputEmail, inputPassword, userList, context)
        _isUserLoggedIn.value = validationPair.second
        if (isUserLoggedIn.value!!) {
            _currentUser.value = validationPair.first
            _shoeList.value = _currentUser.value!!.shoeList
        }
        return isUserLoggedIn.value!!
    }

    fun performRegistration(loginEmail: String, loginPassword: String, context: Context): Boolean {
        if (registerUseCase(loginEmail, loginPassword, userList, context))
            userList.add(User(loginEmail, loginPassword, defaultShoeList))
        return true
    }


    fun initializeListOfShoes(binding: FragmentShoeListingBinding): List<CardView> {
        val cards = mutableListOf<CardView>()
        _shoeList.value?.forEach {
            cards.add(createCardViewForShoeUseCase(it, binding.root.context))
        }
        return cards
    }

    fun addShoe(shoe: Shoe): Boolean {
        if (shoe.name.isNotBlank() && shoe.size in 1.0..60.0 && shoe.company.isNotBlank() && shoe.description.isNotBlank()) {
            val list = _shoeList.value?.toMutableList()
            list?.let {
                it.add(0, shoe)
                userList[userList.indexOf(_currentUser.value!!)].shoeList = list
                _shoeList.value = list
                return true
            }
        }
        return false
    }

    fun onUserLoggedOut() {
        _currentUser.value = null
        _shoeList.value = defaultShoeList
        _isUserLoggedIn.value = false
    }
}