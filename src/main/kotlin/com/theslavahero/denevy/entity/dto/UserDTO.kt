package com.theslavahero.denevy.entity.dto

import com.theslavahero.denevy.entity.User

/**
 * Created by theslavahero on 28.04.22
 */
class UserDTO(
    var name: String,
    var surname: String
) : DTO(), Convertable<User> {
    override fun convert(): User {
        return User(name, surname)
    }
}