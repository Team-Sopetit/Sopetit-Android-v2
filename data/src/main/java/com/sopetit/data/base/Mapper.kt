package com.sopetit.data.base

interface Mapper<RESPONSE, MODEL> {
    fun mapToModel(response: RESPONSE?): MODEL
}