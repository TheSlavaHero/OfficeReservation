package com.theslavahero.denevy.entity

import javax.persistence.*

/**
 * Created by theslavahero on 26.04.22
 */
@Entity
@Table(name = "reservations")
data class Reservation(@Id
                       @GeneratedValue(strategy = GenerationType.IDENTITY)
                       val id: Long?,
                       val userId: Int,
                       val officeId: Int,
                       val reservationStart: String,
                       val reservationFinish: String)
