package rs.raf.projekatjun.david_maloparac_rn9519.presentation.recycler

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekatjun.david_maloparac_rn9519.data.event.Event
import rs.raf.projekatjun.david_maloparac_rn9519.databinding.LayoutItemEventBinding
import timber.log.Timber
import java.lang.StringBuilder

class EventViewHolder (
        private val itemBinding: LayoutItemEventBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(event: Event, onClick: (event: Event) -> Unit, onLongClick: (event: Event) -> Unit) {
        itemBinding.txtEventName.text = event.name
        itemBinding.txtEventDesc.text = event.description
        itemBinding.txtEventUrl.text = event.url

        val sb = StringBuilder()
        sb.append(event.date).append(", ").append(event.time)
        itemBinding.txtEventDate.text = sb.toString()

        var color: String = "#51FFFFFF"
        if(event.priority == "High") {
            color = "#51FF0000"
        } else if(event.priority == "Medium") {
            color = "#514DFF00"
        }
        itemBinding.root.setBackgroundColor(Color.parseColor(color))

        itemBinding.btnDelete.setOnClickListener {
            onClick.invoke(event)
        }

        itemBinding.root.setOnLongClickListener {
            onLongClick.invoke(event)
            false
        }
    }
}