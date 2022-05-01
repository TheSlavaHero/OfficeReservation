package com.theslavahero.denevy.controller

import com.theslavahero.denevy.entity.dto.ReservationDTO
import com.theslavahero.denevy.entity.repository.ReservationRepository
import mu.KotlinLogging
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

    @Autowired
    private val reservationController: ReservationController,
    @Autowired
    private val reservationRepository: ReservationRepository
) {
    private val log = KotlinLogging.logger {}

    @Test
    fun createReservation() {
    }

    @Test
    fun deleteReservation() {
    }

    @Test
    fun getReservation() {
    }

    @Test
    fun getReservationRepository() {
    }

    @Test
    fun checkCorrectTime() {
        for (startHours in 0..23) {
            for (finishHours in 0..23) {
                val start: LocalDateTime = LocalDateTime.now().withHour(startHours)
                val finish: LocalDateTime = LocalDateTime.now().withHour(finishHours)
                val reservationDto = ReservationDTO(start, finish)
                if (startHours < 8 || startHours > 16 ||
                    finishHours < 8 || finishHours > 16
                ) {
                    log.info { "Exception should be thrown with: Start = $startHours, finish = $finishHours" }
                    assertThrows<HttpClientErrorException> { reservationController.checkCorrectTime(reservationDto) }
                } else reservationController.checkCorrectTime(reservationDto)
            }
        }
    }
}