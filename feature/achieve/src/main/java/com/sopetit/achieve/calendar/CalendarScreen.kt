package com.sopetit.achieve.calendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CalendarScreen() {
    CalendarContent()
}

@Composable
fun CalendarContent() {}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCalendar() {
    CalendarContent()
}