package com.theslavahero.denevy.controller

import com.theslavahero.denevy.entity.User
import com.theslavahero.denevy.entity.dto.UserDTO
import com.theslavahero.denevy.entity.repository.UserRepository
import mu.KotlinLogging
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

/**
 * Created by theslavahero on 26.04.22
 */
@RestController
@RequestMapping("/api/user")
class UserController(val userRepository: UserRepository) {
    private val log = KotlinLogging.logger {}

    @GetMapping("/get/{id}")//exception if this id does not exist
    @ResponseStatus(HttpStatus.OK)
    fun getUser(@PathVariable id: Long): User {
        log.info { "Getting user with id: $id" }
        return userRepository.getById(id)
    }

    @PutMapping("/create")
    fun createUser(@RequestBody userDTO: UserDTO): ResponseEntity<String> {//throw an exception if user has a preset id
        val user = userRepository.save(userDTO.convert())
        log.info { "Creating new user: $userDTO" }
        return ResponseEntity("A new user with id = ${user.id} has been successfully created", HttpStatus.CREATED)
    }

    @DeleteMapping("/delete/{id}")//exception if this id does not exist//204
    @ResponseStatus(HttpStatus.OK)
    fun deleteUser(@PathVariable id: Long) {
        log.info { "Deleting user with id: $id" }
        return userRepository.deleteById(id)
    }

    @ExceptionHandler(value = [(EntityNotFoundException::class), (EmptyResultDataAccessException::class)])
    fun handleNotFoundEntity(): ResponseEntity<String> {
        return ResponseEntity("Entity with this id was not found", HttpStatus.NOT_FOUND)
    }
}