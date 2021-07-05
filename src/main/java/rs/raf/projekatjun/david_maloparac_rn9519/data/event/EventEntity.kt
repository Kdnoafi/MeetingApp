package rs.raf.projekatjun.david_maloparac_rn9519.data.event

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class EventEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
    val url: String,
    val priority: String,
    val date: String,
    val time: String
)