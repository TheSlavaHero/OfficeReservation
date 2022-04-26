package com.theslavahero.denevy.entity.repository

import com.theslavahero.denevy.entity.Office
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by theslavahero on 26.04.22
 */
interface OfficeRepository: JpaRepository<Office, Long> {
}