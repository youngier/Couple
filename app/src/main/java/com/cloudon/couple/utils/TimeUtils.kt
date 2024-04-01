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

    fun numberToMonth(number: Int) = when(number) {
        1 -> "一月"
        2 -> "二月"
        3 -> "三月"
        4 -> "四月"
        5 -> "五月"
        6 -> "六月"
        7 -> "七月"
        8 -> "八月"
        9 -> "九月"
        10 -> "十月"
        11 -> "十一月"
        12 -> "十二月"
        else -> ""
    }

    fun numberToDay(number: Int) =
        when (number) {
            1 -> "初一"
            2 -> "初二"
            3 -> "初三"
            4 -> "初四"
            5 -> "初五"
            6 -> "初六"
            7 -> "初七"
            8 -> "初八"
            9 -> "初九"
            10 -> "初十"
            11 -> "十一"
            12 -> "十二"
            13 -> "十三"
            14 -> "十四"
            15 -> "十五"
            16 -> "十六"
            17 -> "十七"
            18 -> "十八"
            19 -> "十九"
            20 -> "二十"
            21 -> "廿一"
            22 -> "廿二"
            23 -> "廿三"
            24 -> "廿四"
            25 -> "廿五"
            26 -> "廿六"
            27 -> "廿七"
            28 -> "廿八"
            29 -> "廿九"
            30 -> "三十"
            else -> ""
        }
}