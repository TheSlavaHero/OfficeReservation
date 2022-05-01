package com.theslavahero.denevy.entity.dto

/**
 * Created by theslavahero on 28.04.22
 * O - object
 * D - DTO
 */
interface Convertable<E> {
    fun convert(): E

}