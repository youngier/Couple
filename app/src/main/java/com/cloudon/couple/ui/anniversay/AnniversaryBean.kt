package com.cloudon.couple.ui.anniversay

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * 纪念日表
 */
@Entity(tableName = "anniversary_info")
data class AnniversaryBean (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    // 标题
    var title: String = "",
    // 具体内容
    var description: String? = "",
    // 日期，以时间戳记录
    var time: Long = 0,
    // 是否启用农历计算 0为不使用，1为使用
    var lunar: Int = 0,
    // 纪念日类型
    var type: String = "",
    // 是否启用重复 0为不使用，1为使用
    var repeat: Int = 0,
    // 是否启用提醒
    var notice: Int = 0,
    // 提前提醒类型
    var noticeType: Int = 0,
    // 是否开启置顶
    var top: Int = 0,
): Serializable