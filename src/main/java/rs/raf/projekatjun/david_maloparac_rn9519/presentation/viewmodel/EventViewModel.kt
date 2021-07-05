package rs.raf.projekatjun.david_maloparac_rn9519.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekatjun.david_maloparac_rn9519.data.event.EventEntity
import rs.raf.projekatjun.david_maloparac_rn9519.data.event.EventRepository
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.contract.EventContract
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.states.AddEventState
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.states.EventsState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class EventViewModel (
        private val eventRepository: EventRepository
) : ViewModel(), EventContract.ViewModel {

    companion object {
        var counter = 0
    }

    private val subscriptions = CompositeDisposable()
    override val eventsState: MutableLiveData<EventsState> = MutableLiveData()
    override val addDone: MutableLiveData<AddEventState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
                .debounce(200, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap {
                    eventRepository
                            .getAll()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnError {
                                Timber.e("Error in publish subject")
                                Timber.e(it)
                            }
                }
                .subscribe(
                        {
                            eventsState.value = EventsState.Success(it)
                        },
                        {
                            eventsState.value = EventsState.Error("Error happened while fetching data from db")
                            Timber.e(it)
                        }
                )
        subscriptions.add(subscription)
    }

    override fun getAllEvents() {
        val subscription = eventRepository
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            eventsState.value = EventsState.Success(it)
                        },
                        {
                            eventsState.value = EventsState.Error("Error happened while fetching data from db")
                            Timber.e(it)
                        }
                )
        subscriptions.add(subscription)
    }

    override fun addEvent(event: EventEntity) {
        val subscription = eventRepository
                .insert(event)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            addDone.value = AddEventState.Success
                        },
                        {
                            addDone.value = AddEventState.Error("Error happened while adding event")
                            Timber.e(it)
                        }
                )
        subscriptions.add(subscription)
    }

    override fun removeEvent(event: EventEntity) {
        val subscription = eventRepository
                .delete(event)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            addDone.value = AddEventState.Success
                        },
                        {
                            addDone.value = AddEventState.Error("Error happened while adding dish")
                            Timber.e(it)
                        }
                )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}