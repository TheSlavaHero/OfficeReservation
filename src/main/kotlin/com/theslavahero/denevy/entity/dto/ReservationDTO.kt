package com.theslavahero.denevy.entity.dto

import com.theslavahero.denevy.entity.Reservation

/**
 * Created by theslavahero on 28.04.22
 */
class ReservationDTO : DTO(), Convertable<ReservationDTO, Reservation> {
    override fun convertTo(firstObject: ReservationDTO): Reservation {
        TODO("Not yet implemented")
    }

    override fun convertFrom(secondObject: Reservation): ReservationDTO {
        TODO("Not yet implemented")
    }

}