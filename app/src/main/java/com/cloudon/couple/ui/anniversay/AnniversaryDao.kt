package com.cloudon.couple.ui.anniversay

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnniversaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bean: AnniversaryBean): Long

    @Query("select * from anniversary_info")
    suspend fun query(): MutableList<AnniversaryBean>

}