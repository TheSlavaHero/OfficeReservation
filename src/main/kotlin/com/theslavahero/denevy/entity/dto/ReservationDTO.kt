package com.theslavahero.denevy.entity.dto

import com.theslavahero.denevy.entity.Reservation
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
    constructor(reservationStart: LocalDateTime, reservationFinish: LocalDateTime) : this(
        null,
        null,
        reservationStart,
        reservationFinish
    )

    override fun convert(): Reservation {
        TODO("Not yet implemented")
    }


}