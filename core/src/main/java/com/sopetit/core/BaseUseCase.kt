package com.sopetit.core

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<REQUEST, RESPONSE> {
    abstract suspend operator fun invoke(request: REQUEST): Flow<RESPONSE>
}