package com.theslavahero.denevy.controller

import com.theslavahero.denevy.entity.Reservation
import com.theslavahero.denevy.entity.repository.OfficeRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by theslavahero on 26.04.22
 */
@RestController
@RequestMapping("/api/office")
class OfficeController(val officeRepository: OfficeRepository) {

    @GetMapping("/{id}/reservations")
    fun getAllReservations(@PathVariable id: Long): List<Reservation>? {
        return officeRepository.getById(id).reservations
    }
    //getAllReservationForThisTime//GET
//    @GetMapping("/{officeId}/reservationOnTime")
//    fun checkOfficeReservationForSpecificTime(@PathVariable officeId: Long, @RequestParam date: Date): List<Reservation>  {
//
//    }
//
//    //getFreeTimeForThisOfficeForThisDay//GET
//    @GetMapping("/{id}/freeTime")
//    fun getAllReservationsForToday(@PathVariable id: Long):  {
//        return officeRepository.getById(id).reservations
//    }
//    //getFreeTimeForThisOfficeForThisWeek//GET
//    @GetMapping("/{id}/freeTimeWeek")
//    fun getFreeTimeForThisOffice(@PathVariable id: Long): {
//        return officeRepository.getById(id).reservations
//    }
//    //think about all possible exceptions
}