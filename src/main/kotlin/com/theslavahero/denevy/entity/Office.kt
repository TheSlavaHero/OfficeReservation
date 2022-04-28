package com.theslavahero.denevy.entity

import java.util.*
import javax.persistence.*

/**
 * Created by theslavahero on 26.04.22
 */
@Entity
@Table(name = "offices")
class Office(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val cabinetNumber: Int,
    @OneToMany(mappedBy = "officeId")
    val reservations: List<Reservation>
) {
    constructor(cabinetNumber: Int) : this(null, cabinetNumber, Collections.emptyList()) {

    }
}