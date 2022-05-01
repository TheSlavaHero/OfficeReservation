package com.theslavahero.denevy.entity

import java.time.LocalDateTime
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
    private var reservationStart: LocalDateTime,
    private var reservationFinish: LocalDateTime
) {
    override fun toString(): String {
        return "Reservation(id=$id, userId=$userId, office=$office, reservationStart=$reservationStart, reservationFinish=$reservationFinish)"
    }
}
