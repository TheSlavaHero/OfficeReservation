package com.theslavahero.denevy.entity

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
    var cabinetNumber: Int?,
    @OneToMany(mappedBy = "office")
    var reservations: List<Reservation>?
) {
    constructor(id: Long?) : this(id, null, null)
    constructor(id: Long?, cabinetNumber: Int) : this(id, cabinetNumber, null)

    override fun toString(): String {
        return "Office(id=$id, cabinetNumber=$cabinetNumber, reservations=$reservations)"
    }

}