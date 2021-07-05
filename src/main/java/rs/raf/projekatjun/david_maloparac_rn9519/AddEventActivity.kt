package rs.raf.projekatjun.david_maloparac_rn9519

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TaskStackBuilder
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekatjun.david_maloparac_rn9519.data.event.EventEntity
import rs.raf.projekatjun.david_maloparac_rn9519.databinding.ActivityAddEventBinding
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.viewmodel.EventViewModel
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.viewmodel.TimeViewModel
import java.util.*


class AddEventActivity : AppCompatActivity() {

    private val timeViewModel: TimeViewModel by viewModel()
    private val eventViewModel: EventViewModel by viewModel()

    private lateinit var binding: ActivityAddEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    @SuppressLint("ResourceType")
    private fun init() {
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, R.array.cities)
        val txtLocation = binding.txtLocation
        txtLocation.threshold = 1
        txtLocation.setAdapter(adapter)

        initListeners()
    }

    @SuppressLint("CommitPrefEdits", "ShowToast")
    private fun initListeners() {
        binding.btnCheck.setOnClickListener {
            val cities = resources.getStringArray(R.array.cities)
            var found = false

            for(city in cities) {
                if(binding.txtLocation.text.toString() == city) {
                    found = true

                    val thread = Thread {
                        timeViewModel.time = ""
                        timeViewModel.fetchTime(binding.txtLocation.text.toString())
                    }
                    thread.start()

                    Thread(Runnable {
                        this@AddEventActivity.runOnUiThread(java.lang.Runnable {
                            while(true) {
                                if(timeViewModel.time != "") {
                                    binding.btnCheck.text = timeViewModel.time
                                    break
                                }
                            }
                        })
                    }).start()

                    break;
                }
            }

            if(!found) {
                Toast.makeText(baseContext, "Invalid location", Toast.LENGTH_SHORT).show()
            }
                /*Thread(Runnable {
                    this@AddEventActivity.runOnUiThread(java.lang.Runnable {
                        binding.btnCheck.text = "Invalid location"
                    })
                }).start()

                val handler = Handler()
                handler.postDelayed({
                    binding.btnCheck.text = "Check for location"
                }, 2000)
            }*/
        }

        binding.btnSave.setOnClickListener {
            val event = EventEntity(0, binding.txtName.text.toString(), binding.txtDesc.text.toString(), binding.txtUrl.text.toString(),
                binding.spinner.selectedItem.toString(), binding.btnDate.text.toString(), binding.btnTime.text.toString())
            eventViewModel.addEvent(event)

            TaskStackBuilder.create(this)
                    .addParentStack(AddEventActivity::class.java)
                    .addNextIntent(Intent(this, AddEventActivity::class.java))
                    .addNextIntent(Intent(this, MainActivity::class.java))
                    .startActivities()

            finish()
        }

        binding.btnDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val sb = StringBuilder()
                sb.append(dayOfMonth).append("/").append(month + 1).append("/").append(year)

                binding.btnDate.text = sb.toString()
            }, year, month, day)

            dpd.show()
        }

        binding.btnTime.setOnClickListener {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val sb = StringBuilder()
                sb.append(hourOfDay).append(":").append(minute)

                binding.btnTime.setText(sb.toString())
            }, hour, minute, true)

            tpd.show()
        }
    }
}