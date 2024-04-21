package com.cloudon.couple.ui.anniversay.type

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * 纪念日分类表
 */
@Entity(tableName = "anniversary_type_info")
data class TypeBean (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    // 标题
    var title: String = "",
): Serializable