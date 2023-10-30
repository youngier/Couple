package com.cloudon.couple.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

object TimeUtils {

    const val FORMAT_DATE = "yyyy年MM月dd日"

    @JvmStatic
    fun getCurrentDate() = timeToStr(System.currentTimeMillis(), FORMAT_DATE)

    @JvmStatic
    fun getCurrentLunar(): String {
        var date = LocalDate.now()
        return LunarCalendarUtil.getLunarMonthDay(
            "${date.year}",
            "${date.monthValue}",
            "${date.dayOfMonth}"
        )
    }

    fun timeToStr(timestamp: Long, formatStr: String) =
        SimpleDateFormat(formatStr).format(Date(timestamp))

}