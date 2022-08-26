package com.habbashmahmood.clean_note_app.feature_note.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : NoteOrder(orderType)
    class Date(orderType: OrderType) : NoteOrder(orderType)
    class Color(orderType: OrderType) : NoteOrder(orderType)

    fun toggle(noteOrder: NoteOrder): NoteOrder {
        val orderType = if (noteOrder.orderType == OrderType.Ascending) {
            OrderType.Descending
        } else {
            OrderType.Ascending
        }

        return when (this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
