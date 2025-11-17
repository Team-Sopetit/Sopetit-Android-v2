package com.sopetit.splash.model

import androidx.compose.runtime.Composable

data class SplashVersionModel(
    val colorVersion: Int,
    val bottomContent: @Composable () -> Unit,
)
