package com.theslavahero.denevy.entity

import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

/**
 * Created by theslavahero on 26.04.22
 */
@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var name: String?,
    var surname: String?,
    @OneToMany(mappedBy = "userId")
    private var reservations: List<Reservation>?
) {
    constructor(id: Long?) : this(id, null, null, null)
    constructor(name: String, surname: String)
            : this(null, name, surname, Collections.emptyList())

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