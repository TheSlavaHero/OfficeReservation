package com.theslavahero.denevy.controller

import com.theslavahero.denevy.entity.Reservation
import com.theslavahero.denevy.entity.repository.OfficeRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

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

    @ExceptionHandler(value = [(EntityNotFoundException::class), (EmptyResultDataAccessException::class)])
    fun handleNotFoundEntity(): ResponseEntity<String> {
        return ResponseEntity("Entity with this id was not found", HttpStatus.NOT_FOUND)
    }
}