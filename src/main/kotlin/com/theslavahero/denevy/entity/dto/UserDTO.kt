package com.theslavahero.denevy.entity.dto

import com.theslavahero.denevy.entity.User

/**
 * Created by theslavahero on 28.04.22
 */
class UserDTO : DTO(), Convertable<UserDTO, User> {
    override fun convertTo(firstObject: UserDTO): User {
        TODO("Not yet implemented")
    }

    override fun convertFrom(secondObject: User): UserDTO {
        TODO("Not yet implemented")
    }
}