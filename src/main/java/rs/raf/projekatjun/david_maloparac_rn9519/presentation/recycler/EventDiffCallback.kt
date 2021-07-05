package rs.raf.projekatjun.david_maloparac_rn9519.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekatjun.david_maloparac_rn9519.data.event.Event

class EventDiffCallback : DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.name == newItem.name
    }
}