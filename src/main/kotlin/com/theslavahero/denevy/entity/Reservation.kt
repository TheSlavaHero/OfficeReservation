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
    var id: Long?,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var userId: User,
    @ManyToOne
    @JoinColumn(name = "office_id")
    var office: Office,
    var reservationStart: LocalDateTime,
    var reservationFinish: LocalDateTime
) {
    override fun toString(): String {
        return "Reservation(id=$id, userId=${userId.id}, office=${office.id}, reservationStart=$reservationStart, reservationFinish=$reservationFinish)"
    }
}
