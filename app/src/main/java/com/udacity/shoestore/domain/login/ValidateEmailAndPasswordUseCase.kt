package com.udacity.shoestore.domain.login

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import java.util.regex.Pattern

class ValidateEmailAndPasswordUseCase {
    operator fun invoke(
        emailToValidate: String,
        passwordToValidate: String,
        context: Context
    ): Boolean {
        if (emailToValidate.isEmpty()
            || !(Patterns.EMAIL_ADDRESS.matcher(emailToValidate).matches())
        ) {
            Toast.makeText(context, "Incorrect email format", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!isValidPasswordFormat(passwordToValidate)) {
            Toast.makeText(
                context, "Password should be at least 8 characters, contain at least " +
                        "1 digit, 1 lower case letter, 1 uppercase letter, 1 special character",
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        return true
    }

    private fun isValidPasswordFormat(password: String): Boolean {
        val passwordREGEX = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$"
        )
        return passwordREGEX.matcher(password).matches()
    }
}