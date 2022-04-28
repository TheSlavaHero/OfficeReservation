package com.theslavahero.denevy.entity.repository

import com.theslavahero.denevy.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by theslavahero on 26.04.22
 */
@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun getByName(name: String): User
}