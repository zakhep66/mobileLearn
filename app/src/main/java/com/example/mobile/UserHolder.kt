package com.example.firstlesson

import androidx.annotation.VisibleForTesting

object UserHolder {

    private val map = mutableMapOf<String, User>()
    private val phoneFormat = Regex("""^[+][\d]{11}""")

    fun registerUser(
        fullName: String,
        email: String,
        password: String
    ): User = User.makeUser(fullName, email = email, password = password)
        .also { user ->
            if (map.containsKey(user.login)) throw IllegalArgumentException("A user with this email already exists")
            else map[user.login] = user
        }

    fun loginUser(login: String, password: String): String? {
        val phoneLogin = cleanPhone(login)
        return if (phoneLogin.isNotEmpty()) {
            map[phoneLogin]
        } else {
            map[login.trim()]
        }?.let {
            if (it.checkPassword(password)) it.userInfo
            else null
        }
    }


    fun registerUserByPhone(fullName: String, rawPhone: String): User{
        if (rawPhone.matches(phoneFormat))
            if (fullName !== "")
                return User.makeUser(fullName, phone = rawPhone)
            else
                throw IllegalArgumentException("Name shouldn't be empty")
        else
            throw IllegalArgumentException("Phone number has wrong format")
    }

    fun requestAccessCode(login: String) {
        val user = mutableMapOf < String, User > ()[login]
        if (user !== null){
            user.generateAccessCode()
        } else {
            throw IllegalArgumentException("User doesn't exists")
        }
    }


    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder() {
        map.clear()
    }

    private fun cleanPhone(phone: String): String {
        return phone.replace("""[^+\d]""".toRegex(), "")
    }
}