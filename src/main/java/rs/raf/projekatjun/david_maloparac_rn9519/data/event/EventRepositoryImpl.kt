package rs.raf.projekatjun.david_maloparac_rn9519.data.event

import io.reactivex.Completable
import io.reactivex.Observable

class EventRepositoryImpl (
        private val localDataSource: EventDao
) : EventRepository {

    override fun getAll(): Observable<List<Event>> {
        return localDataSource
                .getAll()
                .map {
                    it.map {
                        Event(it.id, it.name, it.description, it.url, it.priority, it.date, it.time)
                    }
                }
    }

    override fun insert(event: EventEntity): Completable {
        return localDataSource
                .insert(event)
    }

    override fun delete(event: EventEntity): Completable {
        return localDataSource
                .delete(event.id)
    }
}