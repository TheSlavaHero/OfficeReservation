package com.theslavahero.denevy.controller

import com.theslavahero.denevy.entity.User
import com.theslavahero.denevy.entity.dto.UserDTO
import com.theslavahero.denevy.entity.repository.UserRepository
import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import java.util.*
import javax.transaction.Transactional


/**
 * Created by theslavahero on 28.04.22
 */
@SpringBootTest
@Transactional
class UserControllerTest(
    @Autowired
    private val userController: UserController,
    @Autowired
    private val userRepository: UserRepository
) {
    @Before
    fun createUser() {
        val userDTO = UserDTO("test", "user")
        userController.createUser(userDTO)
    }

    @Test
    fun testCreateUser() {
        val userDTO = UserDTO("test", "user")
        userController.createUser(userDTO)
        val userFromDatabase = userRepository.getByName("test")
        Assertions.assertEquals(userFromDatabase.name, userDTO.name, "user name")
        Assertions.assertEquals(userFromDatabase.surname, userDTO.surname, "user surname")
        userRepository.deleteAllByName("test")
    }

    @Test
    fun getUser() {
        val user = User("test", "user", Collections.emptyList())
        userRepository.save(user)
        val controllerUser = userController.getUser(1)
        Assertions.assertEquals(user, controllerUser)
        userRepository.deleteAllByName("test")
    }

    @Test
    fun deleteUser() {
        userController.createUser(UserDTO("test", "user"))
        val user = userRepository.getByName("test")
        userController.deleteUser(user.id!!)
        assertThrows<JpaObjectRetrievalFailureException> { userRepository.getById(user.id!!) }
        userRepository.deleteAllByName("test")
    }

}