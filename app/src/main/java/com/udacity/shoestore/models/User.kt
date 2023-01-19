package com.udacity.shoestore.models

data class User(val email: String, val password: String, var shoeList: List<Shoe>) {

}