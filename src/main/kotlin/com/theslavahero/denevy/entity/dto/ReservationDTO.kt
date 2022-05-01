package com.theslavahero.denevy.entity.dto

import com.theslavahero.denevy.entity.Office
import com.theslavahero.denevy.entity.Reservation
import com.theslavahero.denevy.entity.User
import java.time.LocalDateTime

/**
 * Created by theslavahero on 28.04.22
 */
class ReservationDTO(
    var officeId: Long?,
    var userId: Long?,
    var reservationStart: LocalDateTime,
    var reservationFinish: LocalDateTime
) : DTO(), Convertable<Reservation> {

    override fun convert(): Reservation {
        return Reservation(null, User(userId), Office(officeId), reservationStart, reservationFinish)
    }


}