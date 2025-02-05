package com.sopetit.core

interface BaseMapper<RESPONSE, MODEL> {
    fun mapToModel(response: RESPONSE?): MODEL
}