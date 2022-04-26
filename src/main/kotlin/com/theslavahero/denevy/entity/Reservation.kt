package com.theslavahero.denevy.entity

import javax.persistence.*

/**
 * Created by theslavahero on 26.04.22
 */
@Entity
@Table(name = "reservations")
data class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val userId: User,
    @ManyToOne
    @JoinColumn(name = "office_id")
    val officeId: Office,
    val reservationStart: String,
    val reservationFinish: String
)
