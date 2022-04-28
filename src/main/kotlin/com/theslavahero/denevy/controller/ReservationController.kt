package com.theslavahero.denevy.controller

import com.theslavahero.denevy.entity.Reservation
import com.theslavahero.denevy.entity.repository.ReservationRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * Created by theslavahero on 26.04.22
 */
@RestController
@RequestMapping("/api/reservation")
class ReservationController(val reservationRepository: ReservationRepository) {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createReservation(@RequestBody reservation: Reservation) {//check if time for the office is available
        reservationRepository.save(reservation)
    }

    //delete reservation//DELETE
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteReservation(@PathVariable id: Long) {
        reservationRepository.deleteById(id)
    }

    //get info about reservation//GET
    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getReservation(@PathVariable id: Long) {
        reservationRepository.getById(id)
    }
    //think about all possible exceptions
}