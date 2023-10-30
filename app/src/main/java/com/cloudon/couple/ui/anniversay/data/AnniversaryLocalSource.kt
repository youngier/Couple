package com.cloudon.couple.ui.anniversay.data

import com.cloudon.couple.ui.anniversay.AnniversaryBean

class AnniversaryLocalSource {

    suspend fun saveAnniversary(bean: AnniversaryBean) {
        AnniversaryDatabase.db?.getDao()?.insert(bean)
    }

    suspend fun getAllAnniversary(): MutableList<AnniversaryBean> {
        return AnniversaryDatabase.db?.getDao()?.query() ?: mutableListOf()
    }

}