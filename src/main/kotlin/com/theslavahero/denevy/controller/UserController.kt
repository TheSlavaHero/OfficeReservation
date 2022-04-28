package com.theslavahero.denevy.controller

import com.theslavahero.denevy.entity.User
import com.theslavahero.denevy.entity.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by theslavahero on 26.04.22
 */
@RestController
@RequestMapping("/api/user")
class UserController(val userRepository: UserRepository) {
    //get all info about user//GET

    //create new user//PUT
    @PutMapping("/create")
    fun createUser(@RequestBody user: User): ResponseEntity<String> {//throw an exception if user has a preset id
        userRepository.save(user)
        return ResponseEntity("A new User has been successfully created", HttpStatus.CREATED)
    }
    //delete user//DELETE

    //think about all possible exceptions
}