package rs.raf.projekatjun.david_maloparac_rn9519.data.event

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class EventDao {

    @Query("SELECT * FROM events")
    abstract fun getAll(): Observable<List<EventEntity>>

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(event: EventEntity): Completable

    @Query("DELETE FROM events WHERE id LIKE :eventId")
    abstract fun delete(eventId: Int): Completable
}