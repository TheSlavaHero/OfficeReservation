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
    private val id: Long?,
    var cabinetNumber: Int,
    @OneToMany(mappedBy = "officeId")
    var reservations: List<Reservation>
) {
    constructor(cabinetNumber: Int) : this(null, cabinetNumber, Collections.emptyList())

}