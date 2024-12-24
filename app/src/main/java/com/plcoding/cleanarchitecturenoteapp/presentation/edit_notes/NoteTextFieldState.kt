package com.plcoding.cleanarchitecturenoteapp.presentation.edit_notes

data class NoteTextFieldState(
    val text : String = "",
    val hint : String = "",
    var isHintVisible : Boolean = false
) {
}