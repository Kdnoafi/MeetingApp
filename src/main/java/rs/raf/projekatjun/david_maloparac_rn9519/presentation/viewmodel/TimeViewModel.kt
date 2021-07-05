package rs.raf.projekatjun.david_maloparac_rn9519.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.Single
import rs.raf.projekatjun.david_maloparac_rn9519.data.time.TimeResponse
import rs.raf.projekatjun.david_maloparac_rn9519.data.time.TimeService
import rs.raf.projekatjun.david_maloparac_rn9519.presentation.contract.TimeContact
import timber.log.Timber

class TimeViewModel(
    private val source: TimeService
) : ViewModel(), TimeContact.ViewModel {
    override var time: String = ""

    /*
    class SharedViewModel : ViewModel() {
        val selected = MutableLiveData<Item>()

        fun select(item: Item) {
            selected.value = item
        }
    }
     */

    override fun fetchTime(city: String): Single<TimeResponse> {
        /*return source
            .getTime(city)
            .doOnNext {
                Timber.e("xx")
                Timber.e(it.toString())
                Timber.e("yy")
                time = it.datetime
            }*/
            /*map {
                Resource.Suc
            }*/



        // valjalo bi try catch ovde
        val resp = source.getTime(city)
        time = resp.blockingGet().datetime

        return resp
    }
}