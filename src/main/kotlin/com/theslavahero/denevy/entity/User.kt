package com.theslavahero.denevy.entity

import org.hibernate.Hibernate
import javax.persistence.*

/**
 * Created by theslavahero on 26.04.22
 */
@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long?,
    private var name: String,
    private var surname: String,
    @OneToMany(mappedBy = "userId")
    private var reservations: List<Reservation>
) {
    constructor(name: String, surname: String, reservations: List<Reservation>)
            : this(null, name, surname, reservations)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , surname = $surname )"
    }
}