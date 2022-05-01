package com.theslavahero.denevy.controller

import com.theslavahero.denevy.entity.dto.ReservationDTO
import com.theslavahero.denevy.entity.repository.OfficeRepository
import com.theslavahero.denevy.entity.repository.ReservationRepository
import com.theslavahero.denevy.entity.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException

/**
 * Created by theslavahero on 26.04.22
 */
@RestController
@RequestMapping("/api/reservation")
class ReservationController(
    val reservationRepository: ReservationRepository,
    val userRepository: UserRepository,
    val officeRepository: OfficeRepository
) {

    val officeReservationStartTime = 8
    val officeReservationEndTime = 16

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createReservation(@RequestBody reservationDTO: ReservationDTO) {//check if time for the office is available + time from 8 to 17
        checkCorrectFormatOfReservation(reservationDTO)
        checkIfOfficeExists(reservationDTO)
        checkForConflictReservations(reservationDTO)
        checkIfUserExists(reservationDTO)
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

    @ExceptionHandler(value = [(HttpClientErrorException::class)])
    fun handleUnprocessableEntityException(exception: HttpClientErrorException): ResponseEntity<String> {
        return ResponseEntity(exception.message, exception.statusCode)
    }

    fun checkCorrectFormatOfReservation(reservationDTO: ReservationDTO) {
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

    private fun checkIfUserExists(reservationDTO: ReservationDTO) {
        try {
            userRepository.getById(reservationDTO.userId!!)
        } catch (e: JpaObjectRetrievalFailureException) {
            throw HttpClientErrorException(
                HttpStatus.UNPROCESSABLE_ENTITY, "User with id ${reservationDTO.userId} is not in the database"
            )
        }
    }

    private fun checkIfOfficeExists(reservationDTO: ReservationDTO) {
        try {
            officeRepository.getById(reservationDTO.officeId!!)
        } catch (e: JpaObjectRetrievalFailureException) {
            throw HttpClientErrorException(
                HttpStatus.UNPROCESSABLE_ENTITY, "Office with id ${reservationDTO.officeId} is not in the database"
            )
        }
    }

    private fun checkForConflictReservations(reservationDTO: ReservationDTO) {
        val officeId = reservationDTO.officeId
        val reservationStart = reservationDTO.reservationStart
        val reservationFinish = reservationDTO.reservationFinish

        val reservationsByStart =
            reservationRepository.findAllByOfficeIdAndReservationStartAfterAndReservationStartBefore(
                officeId!!,
                reservationStart,
                reservationFinish
            )
        val reservationsByFinish =
            reservationRepository.findAllByOfficeIdAndReservationFinishAfterAndReservationFinishBefore(
                officeId,
                reservationStart,
                reservationFinish
            )

        if (!(reservationsByStart.isEmpty() && reservationsByFinish.isEmpty())) {
            throw HttpClientErrorException(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Found conflicting reservations by start time: $reservationsByStart, " +
                        "by finish: $reservationsByFinish"
            )
        }
    }
}