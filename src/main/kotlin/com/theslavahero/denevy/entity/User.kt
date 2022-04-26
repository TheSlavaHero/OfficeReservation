package com.theslavahero.denevy.entity

import javax.persistence.*

/**
 * Created by theslavahero on 26.04.22
 */
@Entity
@Table(name = "users")
data class User(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                val id:Long?,
                val name:String,
                val surname:String)