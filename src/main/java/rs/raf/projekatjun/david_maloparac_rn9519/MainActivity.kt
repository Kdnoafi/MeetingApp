package rs.raf.projekatjun.david_maloparac_rn9519

import android.app.TaskStackBuilder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.projekatjun.david_maloparac_rn9519.databinding.ActivityMainBinding
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.activity.ListActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.btnAdd.setOnClickListener {
            // ovo laga, bolje uradi sa fragmentima umesto sa aktivitijima
            TaskStackBuilder.create(this)
                    .addParentStack(MainActivity::class.java)
                    .addNextIntent(Intent(this, MainActivity::class.java))
                    .addNextIntent(Intent(this, AddEventActivity::class.java))
                    .startActivities()

            finish()
        }

        binding.btnShow.setOnClickListener {
            TaskStackBuilder.create(this)
                    .addParentStack(MainActivity::class.java)
                    .addNextIntent(Intent(this, MainActivity::class.java))
                    .addNextIntent(Intent(this, ListActivity::class.java))
                    .startActivities()

            finish()
        }
    }
}