package com.tdd.customroutine

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomRoutineScreen() {
    CustomRoutineContent()
}

@Composable
fun CustomRoutineContent() {}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCustomRoutine() {
    CustomRoutineContent()
}