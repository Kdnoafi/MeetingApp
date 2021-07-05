package rs.raf.projekatjun.david_maloparac_rn9519.presentation.states

import rs.raf.projekatjun.david_maloparac_rn9519.data.event.Event

sealed class EventsState {

    object Loading: EventsState()
    object DataFetched: EventsState()
    data class Success(val events: List<Event>): EventsState()
    data class Error(val message: String): EventsState()
}