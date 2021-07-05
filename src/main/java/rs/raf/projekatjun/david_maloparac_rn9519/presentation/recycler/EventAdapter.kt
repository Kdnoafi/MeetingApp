package rs.raf.projekatjun.david_maloparac_rn9519.presentation.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekatjun.david_maloparac_rn9519.data.event.Event
import rs.raf.projekatjun.david_maloparac_rn9519.databinding.LayoutItemEventBinding

class EventAdapter : ListAdapter<Event, EventViewHolder>(EventDiffCallback()) {

    lateinit var onItemClick: (event: Event) -> Unit
    lateinit var onLongClick: (event: Event) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemBinding = LayoutItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick, onLongClick)
    }
}