package rs.raf.projekatjun.david_maloparac_rn9519.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekatjun.david_maloparac_rn9519.data.event.EventEntity
import rs.raf.projekatjun.david_maloparac_rn9519.databinding.ActivityListBinding
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.recycler.EventAdapter
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.states.EventsState
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.viewmodel.EventViewModel
import timber.log.Timber

class ListActivity : AppCompatActivity() {

    private val eventViewModel: EventViewModel by viewModel()

    private lateinit var binding: ActivityListBinding

    private lateinit var eventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        val llm = LinearLayoutManager(applicationContext)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.listEvent.layoutManager = llm

        eventAdapter = EventAdapter()
        binding.listEvent.adapter = eventAdapter

        // Klik na dugme za brisanje
        eventAdapter.onItemClick = { event ->
            val entity = EventEntity(event.id, event.name, event.description, event.url, event.priority, event.date, event.time)
            eventViewModel.removeEvent(entity)
        }

        // Long klik na dogadjaj
        eventAdapter.onLongClick = { event ->
            val sb = StringBuilder()
            sb.append(event.name).append("\n").append(event.description).append("\n").
                    append(event.date).append(", ").append(event.time).append("\n").
                    append(event.url)

            val share = Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                type = "message/rfc822"

                putExtra(Intent.EXTRA_TEXT, sb.toString())

                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }, null)

            startActivity(share)
        }
    }

    private fun initObservers() {
        eventViewModel.eventsState.observe(this, Observer {
            renderEventState(it)
        })

        eventViewModel.getAllEvents()
    }

    private fun renderEventState(state: EventsState) {
        when (state) {
            is EventsState.Success -> {
                eventAdapter.submitList(state.events)
            }
            is EventsState.Error -> {
                Toast.makeText(applicationContext, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}