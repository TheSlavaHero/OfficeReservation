package com.theslavahero.denevy.entity.dto

import com.theslavahero.denevy.entity.Reservation
import java.util.*

/**
 * Created by theslavahero on 28.04.22
 */
class ReservationDTO(
    var officeId: Long,
    var userId: Long,
    var reservationStart: Date,
    var reservationFinish: Date
) : DTO(), Convertable<Reservation> {
    override fun convert(): Reservation {
        TODO("Not yet implemented")
    }


}