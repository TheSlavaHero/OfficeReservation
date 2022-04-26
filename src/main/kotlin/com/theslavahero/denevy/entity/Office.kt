package com.theslavahero.denevy.entity

import javax.persistence.*

/**
 * Created by theslavahero on 26.04.22
 */
@Entity
@Table(name = "office")
data class Office(@Id
                  @GeneratedValue(strategy = GenerationType.IDENTITY)
                  val id:Long?,
                  val cabinetNumber:Int)