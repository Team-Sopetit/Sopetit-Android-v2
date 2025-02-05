package com.sopetit.core.base

interface Mapper<RESPONSE, MODEL> {
    fun mapToModel(response: RESPONSE?): MODEL
}