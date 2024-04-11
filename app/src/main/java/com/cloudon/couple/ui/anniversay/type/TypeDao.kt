package com.cloudon.couple.ui.anniversay.type

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bean: TypeBean): Long

    @Query("select * from anniversary_type_info")
    suspend fun query(): MutableList<TypeBean>

    @Query("select * from anniversary_type_info where title = :title")
    suspend fun query(title: String): TypeBean?

    @Delete
    suspend fun delete(bean: TypeBean)

}