package com.theslavahero.denevy.controller

import com.theslavahero.denevy.entity.User
import com.theslavahero.denevy.entity.dto.ReservationDTO
import com.theslavahero.denevy.entity.repository.ReservationRepository
import com.theslavahero.denevy.entity.repository.UserRepository
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.HttpClientErrorException
import java.time.LocalDateTime
import javax.transaction.Transactional

/**
 * Created by theslavahero on 28.04.22
 */
@SpringBootTest
@Transactional
class ReservationControllerTest(

    @Autowired private val reservationController: ReservationController,
    @Autowired private val reservationRepository: ReservationRepository,
    @Autowired private val userRepository: UserRepository
) {
    private val log = KotlinLogging.logger {}

    @BeforeEach
    fun createUser() {
        userRepository.save(User("Test", "User"))
    }

    @Test
    fun createReservationWithIncorrectTime() {// try to create with incorrect time, non-existing office, non-existing user, with occupied reservation
        val reservationDTO = ReservationDTO(
            1, 1,
            LocalDateTime.now().withHour(14),
            LocalDateTime.now().withHour(12)
        )
        assertThrows<HttpClientErrorException> {
            reservationController.createReservation(reservationDTO)
        }
    }

    @Test
    fun createReservationWithLateTime() {
        val reservationDTO = ReservationDTO(
            1, 1,
            LocalDateTime.now().withHour(14),
            LocalDateTime.now().withHour(20)
        )
        assertThrows<HttpClientErrorException> {
            reservationController.createReservation(reservationDTO)
        }
    }

    @Test
    fun createReservationWithNonExistingOffice() {
        val reservationDTO = ReservationDTO(
            100, 1,
            LocalDateTime.now().withHour(14),
            LocalDateTime.now().withHour(16)
        )
        assertThrows<HttpClientErrorException> {
            reservationController.createReservation(reservationDTO)
        }
    }

    @Test
    fun createReservationWithNonExistingUser() {
        val reservationDTO = ReservationDTO(
            1, 100,
            LocalDateTime.now().withHour(14),
            LocalDateTime.now().withHour(16)
        )
        assertThrows<HttpClientErrorException> {
            reservationController.createReservation(reservationDTO)
        }
    }

    @Test
    fun createReservationAndCheckColisions() {
        val reservationDTO = ReservationDTO(
            1, 1,
            LocalDateTime.now().withHour(12),
            LocalDateTime.now().withHour(15)
        )
        reservationController.createReservation(reservationDTO)
        val reservation = reservationRepository.getById(1)
        Assertions.assertEquals(reservation.reservationStart, reservationDTO.convert().reservationStart)
        Assertions.assertEquals(reservation.reservationFinish, reservationDTO.convert().reservationFinish)
        //check for the same reservation
        checkReservationSave(12, 15)
        //check for the reseravtion that starts later
        checkReservationSave(12, 16)
        //all other checks like 2 before
        checkReservationSave(11, 16)
        checkReservationSave(11, 15)
        checkReservationSave(13, 14)
    }

    fun checkReservationSave(start: Int, finish: Int) {
        val reservationDTO = ReservationDTO(
            1, 1,
            LocalDateTime.now().withHour(start),
            LocalDateTime.now().withHour(finish)
        )
        assertThrows<HttpClientErrorException> {
            reservationController.createReservation(reservationDTO)
        }
    }


    @Test
    fun deleteReservation() {
        val reservationDTO = ReservationDTO(
            1, 1,
            LocalDateTime.now().withHour(12),
            LocalDateTime.now().withHour(15)
        )
        reservationController.createReservation(reservationDTO)
        reservationController.deleteReservation(1)
    }

    @Test
    fun getReservation() {
        val reservationDTO = ReservationDTO(
            1, 1,
            LocalDateTime.now().withHour(12),
            LocalDateTime.now().withHour(15)
        )
        reservationController.createReservation(reservationDTO)
        reservationController.getReservation(1)
    }

    @Test
    fun checkCorrectTime() {
        var start: LocalDateTime
        var finish: LocalDateTime
        var reservationDto: ReservationDTO
        for (startHours in 0..23) {
            for (finishHours in 0..23) {
                start = LocalDateTime.now().withHour(startHours)
                finish = LocalDateTime.now().withHour(finishHours)
                reservationDto = ReservationDTO(1, 1, start, finish)
                if (startHours < 8 || startHours > 16 || finishHours < 8 || finishHours > 16 ||
                    reservationDto.reservationStart > reservationDto.reservationFinish
                ) {
                    assertThrows<HttpClientErrorException> {
                        reservationController.checkCorrectFormatOfReservation(reservationDto)
                    }
                } else reservationController.checkCorrectFormatOfReservation(reservationDto)
            }
        }
    }
}