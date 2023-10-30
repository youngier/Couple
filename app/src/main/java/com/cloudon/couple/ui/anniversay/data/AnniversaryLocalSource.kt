package com.cloudon.couple.ui.anniversay.data

import com.cloudon.couple.ui.anniversay.AnniversaryBean

class AnniversaryLocalSource {

    suspend fun saveAnniversary(bean: AnniversaryBean) {
        AnniversaryDatabase.getDatabase().getDao().insert(bean)
    }

    suspend fun getAllAnniversary(): MutableList<AnniversaryBean> {
        return AnniversaryDatabase.getDatabase().getDao().query()
    }

}