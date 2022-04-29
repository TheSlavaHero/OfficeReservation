package com.theslavahero.denevy.entity

import java.util.*
import javax.persistence.*

/**
 * Created by theslavahero on 26.04.22
 */
@Entity
@Table(name = "reservations")
class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,
    @ManyToOne
    @JoinColumn(name = "user_id")
    private var userId: User,
    @ManyToOne
    @JoinColumn(name = "office_id")
    private var office: Office,
    private var reservationStart: Date,
    private var reservationFinish: Date
) {
}
