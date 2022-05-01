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
    //finds all reservations (for current Office by id) that start between dateStart and dateFinish
    fun findAllByOfficeIdAndReservationStartAfterAndReservationStartBefore(
        office_id: Long,
        dateStart: LocalDateTime,
        dateFinish: LocalDateTime
    ): List<Reservation>

    //finds all reservations (for current Office by id) that finish between dateStart and dateFinish
    fun findAllByOfficeIdAndReservationFinishAfterAndReservationFinishBefore(
        office_id: Long,
        dateStart: LocalDateTime,
        dateFinish: LocalDateTime
    ): List<Reservation>
}
