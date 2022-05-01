package com.theslavahero.denevy.entity.dto

import com.theslavahero.denevy.entity.Office

/**
 * Created by theslavahero on 28.04.22
 */
class OfficeDTO(
    private val id: Long?,
    var cabinetNumber: Int,
    var reservationIds: List<Long>
) : DTO(), Convertable<Office> {
    override fun convert(): Office {
//        return Office(cabinetNumber, reservationIds.map { return Reservation() }
        TODO("Not yet implemented")
    }
}