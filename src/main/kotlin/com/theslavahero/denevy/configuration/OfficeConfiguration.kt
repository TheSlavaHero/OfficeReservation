package com.theslavahero.denevy.configuration

import com.theslavahero.denevy.entity.Office
import com.theslavahero.denevy.entity.repository.OfficeRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

/**
 * Created by theslavahero on 26.04.22
 */
@Component
class OfficeConfiguration {
    @Bean
    fun officeInitialization(officeRepository: OfficeRepository) = CommandLineRunner {
        officeRepository.save(Office(1L))
        officeRepository.save(Office(2L))
        officeRepository.save(Office(3L))
        officeRepository.save(Office(4L))
    }
}