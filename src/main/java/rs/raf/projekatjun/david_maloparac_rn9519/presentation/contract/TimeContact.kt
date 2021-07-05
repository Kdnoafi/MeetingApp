package rs.raf.projekatjun.david_maloparac_rn9519.presentation.contract

import io.reactivex.Single
import rs.raf.projekatjun.david_maloparac_rn9519.data.time.TimeResponse

interface TimeContact {

    interface ViewModel {
        var time: String

        fun fetchTime(city: String): Single<TimeResponse>
    }
}