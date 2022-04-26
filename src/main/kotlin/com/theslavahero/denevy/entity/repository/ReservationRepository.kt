package com.theslavahero.denevy.entity.repository

import com.theslavahero.denevy.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by theslavahero on 26.04.22
 */
@Repository
interface ReservationRepository: JpaRepository<Reservation, Long>