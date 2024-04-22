package com.mrjalal.randomgif.domain.repository.model.util

interface Mapper<in T, out R> {
    fun map(input: T): R
}