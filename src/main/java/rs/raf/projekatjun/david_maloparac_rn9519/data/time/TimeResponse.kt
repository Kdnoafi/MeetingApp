package rs.raf.projekatjun.david_maloparac_rn9519.data.time

import com.squareup.moshi.Json

data class TimeResponse (
    @Json(name="datetime")
    val datetime: String
)