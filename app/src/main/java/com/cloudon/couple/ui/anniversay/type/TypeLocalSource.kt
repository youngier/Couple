package com.cloudon.couple.ui.anniversay.type

import com.cloudon.couple.ui.anniversay.data.AnniversaryDatabase

class TypeLocalSource {

    suspend fun saveType(type: String) {
        val result = AnniversaryDatabase.getDatabase().getTypeDao().query(type)
        if (result != null) {
            result.title = type
            AnniversaryDatabase.getDatabase().getTypeDao().insert(result)
        } else {
            AnniversaryDatabase.getDatabase().getTypeDao().insert(TypeBean(
                title = type
            )
            )
        }

    }
    suspend fun removeType(type: String) {
        val result = AnniversaryDatabase.getDatabase().getTypeDao().query(type)
        if (result != null) {
            AnniversaryDatabase.getDatabase().getTypeDao().delete(result)
        }
    }

    suspend fun getAllType(): MutableList<TypeBean> {
        return AnniversaryDatabase.getDatabase().getTypeDao().query()
    }

}