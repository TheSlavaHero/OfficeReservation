package com.theslavahero.denevy.controller

import com.theslavahero.denevy.entity.dto.ReservationDTO
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
    fun createReservation(@RequestBody reservationDTO: ReservationDTO) {//check if time for the office is available
        reservationRepository.save(reservationDTO.convert())
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