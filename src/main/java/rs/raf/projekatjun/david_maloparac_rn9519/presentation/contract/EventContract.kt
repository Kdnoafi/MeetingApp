package rs.raf.projekatjun.david_maloparac_rn9519.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekatjun.david_maloparac_rn9519.data.event.Event
import rs.raf.projekatjun.david_maloparac_rn9519.data.event.EventEntity
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.states.AddEventState
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.states.EventsState

interface EventContract {

    interface ViewModel {

        val eventsState: LiveData<EventsState>
        val addDone: LiveData<AddEventState>

        fun getAllEvents()
        fun addEvent(event: EventEntity)
        fun removeEvent(event: EventEntity)
    }
}