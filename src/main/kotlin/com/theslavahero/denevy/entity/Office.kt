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
    var cabinetNumber: Int?,
    @OneToMany(mappedBy = "office")
    var reservations: List<Reservation>?
) {
    constructor(id: Long?) : this(id, null, null)
    constructor(cabinetNumber: Int) : this(null, cabinetNumber, Collections.emptyList())
    constructor(cabinetNumber: Int, reservations: List<Reservation>) : this(null, cabinetNumber, reservations)

}