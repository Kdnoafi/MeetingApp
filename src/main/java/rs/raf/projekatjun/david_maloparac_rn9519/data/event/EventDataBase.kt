package rs.raf.projekatjun.david_maloparac_rn9519.data.event

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
        entities = [EventEntity::class],
        version = 1,
        exportSchema = false)
@TypeConverters()
abstract class EventDataBase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}