package com.theslavahero.denevy.controller

import com.theslavahero.denevy.entity.dto.ReservationDTO
import com.theslavahero.denevy.entity.repository.ReservationRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException

/**
 * Created by theslavahero on 26.04.22
 */
@RestController
@RequestMapping("/api/reservation")
class ReservationController(val reservationRepository: ReservationRepository) {

    val officeReservationStartTime = 8
    val officeReservationEndTime = 16

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createReservation(@RequestBody reservationDTO: ReservationDTO) {//check if time for the office is available + time from 8 to 17
        checkCorrectTime(reservationDTO)
        reservationRepository.save(reservationDTO.convert())
    }

    fun checkCorrectTime(reservationDTO: ReservationDTO) {
        if (reservationDTO.reservationStart > reservationDTO.reservationFinish)
            throw HttpClientErrorException(
                HttpStatus.UNPROCESSABLE_ENTITY, "Start of the reservation can not be after it's finish"
            )
        val startHours: Int = reservationDTO.reservationStart.hour
        val finishHours: Int = reservationDTO.reservationFinish.hour
        if (startHours < officeReservationStartTime || startHours > officeReservationEndTime ||
            finishHours < officeReservationStartTime || finishHours > officeReservationEndTime
        ) {
            throw HttpClientErrorException(
                HttpStatus.UNPROCESSABLE_ENTITY, "Start and end of the reservation should be " +
                        "between 8 and 17 hours"
            )
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteReservation(@PathVariable id: Long) {
        reservationRepository.deleteById(id)
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getReservation(@PathVariable id: Long) {
        reservationRepository.getById(id)
    }
    //think about all possible exceptions
}