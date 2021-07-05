package rs.raf.projekatjun.david_maloparac_rn9519.data.time

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import rs.raf.projekatjun.david_maloparac_rn9519.data.time.TimeResponse

interface TimeService {

    @GET("api/timezone/Europe/{city}")
    fun getTime(@Path("city") city: String): Single<TimeResponse>
}