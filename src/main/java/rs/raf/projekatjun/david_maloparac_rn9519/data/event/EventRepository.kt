package rs.raf.projekatjun.david_maloparac_rn9519.data.event

import io.reactivex.Completable
import io.reactivex.Observable

interface EventRepository {

    fun getAll(): Observable<List<Event>>
    fun insert(event: EventEntity): Completable
    fun delete(event: EventEntity): Completable
}