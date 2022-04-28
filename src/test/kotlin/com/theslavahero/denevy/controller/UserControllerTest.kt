package com.theslavahero.denevy.controller

import com.theslavahero.denevy.entity.User
import com.theslavahero.denevy.entity.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

/**
 * Created by theslavahero on 28.04.22
 */
@SpringBootTest
class UserControllerTest(
    @Autowired
    private val userController: UserController,
    @Autowired
    private val userRepository: UserRepository
) {
    @Test
    fun testCreateUser() {
        val user = User("Slava", "Yakovenko", Collections.emptyList())
        userController.createUser(user)
        val userFromDatabase = userRepository.getByName("Slava")
        Assertions.assertEquals(user, userFromDatabase, "createUser method")
    }
}