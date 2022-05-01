package com.theslavahero.denevy.entity.repository

import com.theslavahero.denevy.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

/**
 * Created by theslavahero on 26.04.22
 */
@Repository
interface ReservationRepository : JpaRepository<Reservation, Long> {
    fun findAllByOfficeIdAndReservationStartBetween(
        office_id: Long,
        reservationStart: LocalDateTime,
        reservationStart2: LocalDateTime
    ): List<Reservation>

    fun findAllByOfficeIdAndReservationFinishBetween(
        office_id: Long,
        reservationStart: LocalDateTime,
        reservationStart2: LocalDateTime
    ): List<Reservation>

    //finds all reservations (for current Office by id) that start before dateStart and finish after dateFinish
    fun findAllByOfficeIdAndReservationStartBeforeAndReservationFinishAfter(
        office_id: Long,
        dateStart: LocalDateTime,
        dateFinish: LocalDateTime
    ): List<Reservation>
}
